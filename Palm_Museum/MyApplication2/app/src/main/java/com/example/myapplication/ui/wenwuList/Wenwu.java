package com.example.myapplication.ui.wenwuList;

public class Wenwu {
    private String name;
    private String visNum;
    private String wid;
    private String myurl;
    public Wenwu(String name,String visNum,String wid,String myurl){
        this.name=name;
        this.visNum=visNum;
        this.wid=wid;
        this.myurl=myurl;
    }
    public String getName(){
        return name;
    }
    public String getVisNum() { return visNum; }
    public String getWid() { return wid; }
    public String getUrl(){return myurl;}
}
