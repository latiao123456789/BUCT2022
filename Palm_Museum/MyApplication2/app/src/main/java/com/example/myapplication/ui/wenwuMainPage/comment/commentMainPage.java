package com.example.myapplication.ui.wenwuMainPage.comment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DB.DBConnect;
import com.example.myapplication.Global;
import com.example.myapplication.R;
import com.example.myapplication.ui.wenwuList.Wenwu;
import com.example.myapplication.ui.wenwuList.WenwuAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class commentMainPage extends AppCompatActivity {
    private EditText et;
    private Button bt;
    private String wid;
    private List<comment> commentList=new ArrayList<>();
    private Context con;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_page);
        con=this;
        et=(EditText)findViewById(R.id.comment_edittext);
        bt=(Button)findViewById(R.id.push_comment);
        Intent intent=getIntent();
        wid=intent.getStringExtra("wid");
        init();
        initWenWuList();


    }
    private void init(){
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String s=et.getText().toString().trim();
                if(s.equals(""))return;
                et.setText("");
                new Thread(new Runnable(){
                    @Override
                    public void run(){
                        int u=0;
                        try{
                            Connection cn=DBConnect.GetConnection();
                            String sql="insert into comment(uid,wid,content) values("+Global.getuid()+","+wid+",'"+s+"')";
                            PreparedStatement pst;
                            pst=(PreparedStatement)cn.prepareStatement(sql);
                            u=pst.executeUpdate();
                            pst.close();
                            cn.close();
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                        myhandler.sendEmptyMessage(0);
                    }
                }).start();
            }
        });
    }
    public void initWenWuList(){
        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    commentList.clear();
                    Connection cn = DBConnect.GetConnection();
                    String sql = "select * from comment,user where wid="+wid+" and comment.uid=user.uid";
                    Statement st = (Statement)cn.createStatement();
                    ResultSet rs=st.executeQuery(sql);
                    while(rs.next()){
                        comment comt=new comment(rs.getString("username"),rs.getString("wid"),rs.getString("content"),rs.getString("time"),rs.getString("uid"));
                        commentList.add(comt);
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
    private Handler myhandler = new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case 1:
                    RecyclerView recyclerView=(RecyclerView)findViewById(R.id.comment_area);
                    LinearLayoutManager layoutManager=new LinearLayoutManager(con);
                    layoutManager.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    commentAdapter mCommentAdapter=new commentAdapter(commentList);
                    recyclerView.setAdapter(mCommentAdapter);
                    break;
                case 0:
                    initWenWuList();
                    break;
            }
        }
    };
}
