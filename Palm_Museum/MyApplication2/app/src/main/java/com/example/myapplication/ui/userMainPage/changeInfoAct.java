package com.example.myapplication.ui.userMainPage;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.DB.DBConnect;
import com.example.myapplication.Global;
import com.example.myapplication.R;
import com.example.myapplication.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class changeInfoAct extends Activity implements View.OnClickListener{
    private String[] sexArry = {"男","待选","女"};
    private EditText changeUserName;
    private EditText changeUserPwd;
    private EditText changeUserRepwd;
    private TextView changeSex;
    private EditText age;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_ifo);
        initButton();
        initEditText();
        initTextView();
    }
    private void initEditText(){
        changeUserName=(EditText)findViewById(R.id.change_username);
        changeUserPwd=(EditText)findViewById(R.id.change_pwd);
        changeUserRepwd=(EditText)findViewById(R.id.change_newpwd);
        age=(EditText)findViewById(R.id.change_age);
    }
    private void initTextView(){
        changeSex=(TextView) findViewById(R.id.change_sex);
        changeSex.setOnClickListener(v -> {//性别点击后弹出性别选择框
            AlertDialog.Builder builder3 = new AlertDialog.Builder(this);// 自定义对话框
            // checkedItem默认的选中 setSingleChoiceItems设置单选按钮组
            builder3.setSingleChoiceItems(sexArry, 1, (dialog, which) -> {// which是被选中的位置
                // showToast(which+"");
                changeSex.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            });
            builder3.show();// 让弹出框显示
        });
    }
    private void initButton(){
        Button baoCunButton=(Button)findViewById(R.id.change_baocun);
        baoCunButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.change_baocun:
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            Connection cn = DBConnect.GetConnection();
                            String sql = "select * from users where username='"+ Global.getName()+"'";
                            Statement st = (Statement)cn.createStatement();
                            ResultSet rs=st.executeQuery(sql);
                            st.close();
                            cn.close();
                            myhandler.sendEmptyMessage(1);
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }
    private Handler myhandler = new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case 1:
                    break;
            }
        }
    };
}
