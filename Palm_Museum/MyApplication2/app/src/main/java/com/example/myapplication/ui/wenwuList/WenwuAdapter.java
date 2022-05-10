package com.example.myapplication.ui.wenwuList;

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
import com.example.myapplication.ui.wenwuMainPage.wenwuMainPage;

import java.util.List;

public class WenwuAdapter extends RecyclerView.Adapter<WenwuAdapter.ViewHolder>{
    private List<Wenwu> mWenwuList;
    private Context mContext;
    class ViewHolder extends RecyclerView.ViewHolder{
        View wenwuView;
        private ImageView wenwuImage;
        private TextView wenwuName;
        private TextView wenwuVisNum;
        public ViewHolder(@NonNull View view){
            super(view);
            wenwuView = view;
            wenwuImage=(ImageView)view.findViewById(R.id.wenwu_image);
            wenwuName=(TextView)view.findViewById(R.id.wenwu_name);
            wenwuVisNum=(TextView)view.findViewById(R.id.wenwu_visnum);
        }
    }
    public WenwuAdapter(Context mContext,List<Wenwu> WenwuList){
        this.mContext=mContext;
        mWenwuList=WenwuList;
    }
    public void removeData(int position){
        mWenwuList.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.wenwu_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){
        Wenwu wenwu=mWenwuList.get(position);
        holder.wenwuName.setText(wenwu.getName());
        holder.wenwuVisNum.setText("浏览量" + String.valueOf(wenwu.getVisNum()));
        holder.wenwuView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(mContext, wenwuMainPage.class);
                intent.putExtra("wenwu",wenwu.getName());
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount(){
        return mWenwuList.size();
    }
}
