package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.listview.listviewactivity;
import com.example.myapplication.ui.home.HomeAct;


public class LoginAct extends AppCompatActivity {
    private Button myButtonsignup;
    private Button myButtonlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
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
                Intent intent=new Intent(LoginAct.this, HomeAct.class);
                startActivity(intent);
            }
        });
    }
}