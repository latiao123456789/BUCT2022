package com.example.myapplication.ui.wenwuList;

public class Wenwu {
    private String name;
    private int visNum;
    public Wenwu(String name,int visNum){
        this.name=name;
        this.visNum=visNum;
    }
    public String getName(){
        return name;
    }
    public int getVisNum() { return visNum; }
}
