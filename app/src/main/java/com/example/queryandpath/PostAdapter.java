package com.example.queryandpath;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.queryandpath.databinding.PostItemBinding;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    PostItemBinding binding;
    List<Post> postList;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.post_item, parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.title.setText("title :" + post.getTitle());
        holder.id.setText("Id :" + post.getId());
        holder.body.setText("body :" + post.getBody());
        holder.userId.setText("User_Id :" + post.getUserId());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userId, title, id, body;

        public ViewHolder(PostItemBinding itemBinding) {
            super(itemBinding.getRoot());
            title = binding.titleTv;
            userId = binding.userIdTv;
            id = binding.idTv;
            body = binding.bodyTv;
        }
    }
}
