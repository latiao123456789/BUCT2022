package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.os.Message;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.listview.listviewactivity;
import com.example.myapplication.ui.home.HomeAct;


public class LoginAct extends AppCompatActivity {
    private Button myButtonsignup;
    private Button myButtonlogin;
    private EditText usernameEditText;
    private EditText passwordEditText;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case 0x11:
                    Toast.makeText(getApplicationContext(),"密码错误",Toast.LENGTH_SHORT).show();
                    break;
                case 0x12:
                    Intent intent = new Intent(LoginAct.this,HomeAct.class);
                    intent.putExtra("username",msg.obj.toString());
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        String password=getPasswordByUserNameAct.
                                getPasswordByUserName(String.valueOf(R.id.logInUsername));
                        Message message=handler.obtainMessage();
                        String passwordInEditText=passwordEditText.getText().toString();
                        message.obj=usernameEditText.getText().toString();
                        if(password!=null && password.equals(passwordInEditText)){
                            message.what=0x12;
                        }else{
                            message.what=0x11;
                        }
                        handler.sendMessage(message);
                    }
                });
            }
        });
    }
}