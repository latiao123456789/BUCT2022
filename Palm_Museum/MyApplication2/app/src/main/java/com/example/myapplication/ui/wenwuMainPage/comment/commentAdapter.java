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
    private Context mContext;
    class ViewHolder extends RecyclerView.ViewHolder{
        View commentView;
        private TextView commentName;
        private TextView commentText;
        public ViewHolder(@NonNull View view){
            super(view);
            commentName=(TextView)view.findViewById(R.id.comment_name);
            commentText=(TextView)view.findViewById(R.id.comment_text);
        }
    }
    public commentAdapter(Context mContext,List<comment> commentList){
        this.mContext=mContext;
        mCommentList=commentList;
    }
    public void removeData(int position){
        mCommentList.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull commentAdapter.ViewHolder holder, int position){
        comment comt=mCommentList.get(position);
        holder.commentName.setText(comt.getName());
        holder.commentText.setText(comt.getComment());
        holder.commentView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(mContext, wenwuMainPage.class);
                intent.putExtra("wenwu",comt.getName());
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount(){
        return mCommentList.size();
    }
}
