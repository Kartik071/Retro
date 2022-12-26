package com.example.queryandpath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.queryandpath.databinding.ActivityMainBinding;
import com.example.queryandpath.databinding.ShimmerLayoutBinding;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    JSONPlaceholder placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RetrofitInstance();
        binding.shimmerId.startShimmer();
        binding.recyclerView.setVisibility(View.VISIBLE);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // this code will be executed after 2 seconds
                getComments();

            }
        }, 2000);
        binding.shimmerId.stopShimmer();

        binding.Path.setOnClickListener(v -> {
            binding.shimmerId.startShimmer();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    // this code will be executed after 2 seconds
                    getComments();
                }
            }, 2000);


        });
        binding.Query.setOnClickListener(v -> {
            binding.recyclerView.setVisibility(View.INVISIBLE);
            binding.shimmerId.startShimmer();
            Timer();

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    // this code will be executed after 2 seconds
                    getCommentsQuery();

                }
            }, 2000);

        });

    }
void Timer(){
    runOnUiThread(new Runnable() {

        @Override
        public void run() {

            // Stuff that updates the UI
            binding.recyclerView.setVisibility(View.VISIBLE);

        }
    });
//    new Timer().schedule(new TimerTask() {
//        @Override
//        public void run() {
//            // this code will be executed after 2 seconds
//
//
//        }
//    }, 10);
//   // binding.recyclerView.setVisibility(View.VISIBLE);


}

    public void RetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        placeholder = retrofit.create(JSONPlaceholder.class);
    }

    private void getComments() {
        Call<List<Comment>> call = placeholder.getComments(2);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }
                List<Comment> comments = response.body();
                CommentAdapter adapter = new CommentAdapter(comments);
                binding.recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void getCommentsQuery() {
        Call<List<Comment>> call = placeholder.getComments(3);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }
                List<Comment> comments = response.body();
                CommentAdapter adapter = new CommentAdapter(comments);
                binding.recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

}