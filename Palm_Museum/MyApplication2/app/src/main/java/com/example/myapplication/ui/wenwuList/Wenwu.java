package com.example.myapplication.ui.wenwuList;

public class Wenwu {
    private String name;
    private String visNum;
    private String wid;
    public Wenwu(String name,String visNum,String wid){
        this.name=name;
        this.visNum=visNum;
        this.wid=wid;
    }
    public String getName(){
        return name;
    }
    public String getVisNum() { return visNum; }
    public String getWid() { return wid; }
}
