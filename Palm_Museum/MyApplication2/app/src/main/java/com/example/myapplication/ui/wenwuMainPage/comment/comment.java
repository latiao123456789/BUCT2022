package com.example.myapplication.ui.wenwuMainPage.comment;

public class comment {
    private int uid;
    private int wid;
    private String name;
    private String comment;
    private String time;
    private Boolean liked;
    public comment(int uid,int wid,String name,String comment,String time,Boolean liked){
        this.wid=wid;
        this.uid=uid;
        this.name=name;
        this.comment=comment;
        this.time=time;
        this.liked=liked;
    }
    public int getUid(){
        return uid;
    }
    public int getWid(){
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
    public Boolean getLiked(){
        return liked;
    }
}
