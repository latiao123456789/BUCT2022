package com.example.myapplication.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.ui.home.HomeAct;

public class listviewactivity extends Activity {
    private ListView mLv1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mLv1=(ListView)findViewById(R.id.lv_1);
        mLv1.setAdapter(new mylistadapter(listviewactivity.this));
    }
}
