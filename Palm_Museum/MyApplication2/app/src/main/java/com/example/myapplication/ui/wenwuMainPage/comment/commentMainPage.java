package com.example.myapplication.ui.wenwuMainPage.comment;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DB.DBConnect;
import com.example.myapplication.R;
import com.example.myapplication.ui.wenwuList.Wenwu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class commentMainPage extends AppCompatActivity {
    private EditText er;
    private
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_page);

    }
    public void initWenWuList(){
        word=et.getText().toString().trim();
        wenwuList.clear();
        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    Connection cn = DBConnect.GetConnection();
                    String sql = "select * from wenwu where wname like '%"+word+"%'";
                    Log.v("test",sql);
                    Statement st = cn.createStatement();
                    Log.v("test",sql);
                    ResultSet rs=st.executeQuery(sql);
                    Log.v("test",sql);
                    while(rs.next()){
                        Wenwu wenwu=new Wenwu(rs.getString("wname"),Integer.parseInt(rs.getString("visnum")));
                        wenwuList.add(wenwu);
                    }
                    myhandler.sendEmptyMessage(1);
                    st.close();
                    cn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }).start();
    };
}
