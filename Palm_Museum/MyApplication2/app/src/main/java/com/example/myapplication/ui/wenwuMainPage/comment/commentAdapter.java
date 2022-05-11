package com.example.myapplication.ui.wenwuMainPage.comment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.ui.wenwuList.Wenwu;
import com.example.myapplication.ui.wenwuList.WenwuAdapter;
import com.example.myapplication.ui.wenwuMainPage.wenwuMainPage;

import java.util.List;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.ViewHolder>{
    private List<comment> mCommentList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView commentName;
        private TextView commentText;
        private TextView commentTime;
        public ViewHolder(View view){
            super(view);
            commentName=(TextView)view.findViewById(R.id.comment_name);
            commentText=(TextView)view.findViewById(R.id.comment_text);
            commentTime=(TextView)view.findViewById(R.id.comment_time);
        }
    }
    public commentAdapter(List<comment> commentList){
        mCommentList=commentList;
    }
    public void removeData(int position){
        mCommentList.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        comment comt=mCommentList.get(position);
        holder.commentName.setText(comt.getName());
        holder.commentText.setText(comt.getComment());
        holder.commentTime.setText(comt.getTime());
    }
    @Override
    public int getItemCount(){
        return mCommentList.size();
    }
}
