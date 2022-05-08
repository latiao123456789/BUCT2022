package com.example.myapplication.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

public class SignupAct extends AppCompatActivity {
    private Button backToLogin;
    private Button signUpAct;
    private final String words= new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        backToLogin = (Button)findViewById(R.id.signUpBackToLogIn);
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupAct.this,LoginAct.class);
                startActivity(intent);
            }
        });
        signUpAct = (Button)findViewById(R.id.signup);
        signUpAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userName = (EditText)findViewById(R.id.signUpUsername);
                EditText password = (EditText)findViewById(R.id.signUpPassword);
                EditText rePassword = (EditText)findViewById(R.id.signUpRePassword);
                String userNameString = new String(userName.getText().toString());
                String passwordString = new String(password.getText().toString());
                String rePassWord = new String(rePassword.getText().toString());
                if(userNameString.equals(passwordString)){
                    Toast.makeText(getApplicationContext(),"用户名不能与密码相同!" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!passwordString.equals(rePassWord)) {
                    Toast.makeText(getApplicationContext(), "两次输入密码不一致!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userNameString.isEmpty()){
                    Toast.makeText(getApplicationContext(), "用户名不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(passwordString.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "密码不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(passwordString.length()<8){
                    Toast.makeText(getApplicationContext(), "密码不能少于8位!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(passwordString.length()>16){
                    Toast.makeText(getApplicationContext(), "密码不能多于16位!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(userNameString.length()>10){
                    Toast.makeText(getApplicationContext(), "用户名不能超过10位!", Toast.LENGTH_SHORT).show();
                    return;
                }
                /*if(){
                    Toast.makeText(getApplicationContext(), "用户名已存在!", Toast.LENGTH_SHORT).show();
                    return;
                }
                 */

            }
        });
    }
}