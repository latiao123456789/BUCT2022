package com.example.myapplication.ui.wenwuMainPage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DB.DBConnect;
import com.example.myapplication.R;
import com.example.myapplication.ui.login.LoginAct;
import com.example.myapplication.ui.login.SignupAct;
import com.example.myapplication.ui.wenwuMainPage.comment.commentMainPage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class wenwuMainPage extends AppCompatActivity {
    private String intro=new String("");
    private Button fs;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_main_page);
        Intent intent = getIntent();
        String name = intent.getStringExtra("wenwu");
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    Connection cn= DBConnect.GetConnection();
                    String sql="select intro from wenwu where wname ='"+name+"'";
                    Log.v("test",sql);
                    Statement st=cn.createStatement();
                    ResultSet rs=st.executeQuery(sql);
                    rs.next();
                    intro=rs.getString("intro");
                    myhandler.sendEmptyMessage(1);
                    st.close();
                    cn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }).start();
        fs=(Button)findViewById(R.id.comment_btn);
        fs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent(wenwuMainPage.this, commentMainPage.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
    private Handler myhandler = new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case 1:
                    Log.v("test",intro);
                    tv=(TextView)findViewById(R.id.introduce);
                    tv.setText(intro);
                    break;
                case 0:

                    break;
            }
        }
    };
}
