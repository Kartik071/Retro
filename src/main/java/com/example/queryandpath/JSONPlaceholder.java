package com.example.queryandpath;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JSONPlaceholder {
    @GET("comments")
    Call<List<Comment>> getCommentsQuery(@Query("postId") int postId);

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);
}
