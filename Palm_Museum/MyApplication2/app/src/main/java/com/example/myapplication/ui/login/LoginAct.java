package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.*;

import com.example.myapplication.DB.DBConnect;
import com.example.myapplication.R;
import com.example.myapplication.ui.home.HomeAct;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class LoginAct extends AppCompatActivity {
    private Button myButtonsignup;
    private Button myButtonlogin;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Log.v("good","build");
        usernameEditText=(EditText)findViewById(R.id.logInUsername);
        passwordEditText=(EditText)findViewById(R.id.logInPassword);
        myButtonsignup=(Button)findViewById(R.id.btn_2);
        myButtonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginAct.this, SignupAct.class);
                startActivity(intent);
            }
        });
        myButtonlogin=(Button)findViewById(R.id.btn_1);
        myButtonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT).show();
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try{
                            Connection cn= DBConnect.GetConnection();
                            String sql="select password from user where username = '"+usernameEditText.getText().toString().trim()+"'";
                            Statement stmt=cn.createStatement();
                            ResultSet rs=stmt.executeQuery(sql);
                            rs.next();
                            String name=rs.getString("password");
                            Log.v("1aa",name);
                            if(name.equals(passwordEditText.getText().toString().trim())) {
                                myhandler.sendEmptyMessage(1);
                            }
                            else{
                                myhandler.sendEmptyMessage(0);
                            }
                            stmt.close();
                            cn.close();
                        }catch(SQLException e) {
                            myhandler.sendEmptyMessage(0);
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_SHORT).show();
    }
    private Handler myhandler = new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case 1:
                    Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginAct.this,HomeAct.class);
                    intent.putExtra("user",usernameEditText.getText().toString().trim());
                    startActivity(intent);
                    finish();
                    break;
                case 0:
                    passwordEditText.setError("密码错误或用户名不存在！");
                    Log.v("log","bad");
            }
        }
    };
}