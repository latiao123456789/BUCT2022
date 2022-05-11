package com.example.myapplication.ui.wenwuMainPage.comment;

import com.example.myapplication.Global;

public class comment {
    private String uid;
    private String wid;
    private String name;
    private String comment;
    private String time;
    public comment(String name,String wid,String comment,String time,String uid){
        this.wid=wid;
        this.name=name;
        this.comment=comment;
        this.time=time;
        this.uid=uid;
    }
    public String getUid(){
        return uid;
    }
    public String getWid(){
        return wid;
    }
    public String getName(){
        return name;
    }
    public String getComment(){
        return comment;
    }
    public String getTime(){
        return time;
    }
}
