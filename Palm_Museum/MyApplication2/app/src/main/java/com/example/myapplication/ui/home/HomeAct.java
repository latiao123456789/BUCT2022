package com.example.myapplication.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.listview.listviewactivity;
import com.example.myapplication.listview.mylistadapter;

import androidx.annotation.Nullable;

public class HomeAct extends Activity {
    private ListView mLv1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        mLv1=(ListView)findViewById(R.id.lv_2);
        mLv1.setAdapter(new mylistadapter(HomeAct.this));
    }
}
