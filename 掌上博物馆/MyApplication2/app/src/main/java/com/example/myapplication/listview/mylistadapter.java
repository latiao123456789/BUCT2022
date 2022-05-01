package com.example.myapplication.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

public class mylistadapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    public mylistadapter(Context context){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    static class ViewHolder{
        public ImageView imageview;
        public TextView item_name,item_data,item_visnum;
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.listview_item,null);
            holder = new ViewHolder();
            holder.imageview = (ImageView)convertView.findViewById(R.id.iv);
            holder.item_name = (TextView)convertView.findViewById(R.id.tv_name);
            holder.item_data = (TextView)convertView.findViewById(R.id.tv_time);
            holder.item_visnum = (TextView)convertView.findViewById(R.id.tv_num);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.item_name.setText("name:name");
        holder.item_data.setText("data:data");
        holder.item_visnum.setText("visnum:visnum");
        return convertView;
    }
}
