package com.example.queryandpath;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.queryandpath.databinding.RecLayoutBinding;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {


    RecLayoutBinding binding;
    List<Comment> commentList;

    public CommentAdapter(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.rec_layout,parent,false);
    return new CommentHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        Comment comment= commentList.get(position);
        holder.postId.setText(comment.getPostId());
        holder.id.setText(comment.getId());
        holder.commentText.setText(comment.getCommentText());
        holder.name.setText(comment.getName());
        holder.email.setText(comment.getEmail());


    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        TextView postId , id, email , name , commentText;

        public CommentHolder(RecLayoutBinding commentBinding) {

            super(commentBinding.getRoot());
            postId=binding.postId;
            id=binding.commentId;
            email=binding.commentEmail;
            name=binding.userName;
            commentText=binding.commentText;
        }
    }
}
