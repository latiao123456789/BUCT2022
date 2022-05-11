package com.example.myapplication;

import android.app.Application;

public class Global extends Application {
    public static String uid;
    public static String name;
    public static int wenWuInit=0;
    public static int genWuInit(){return wenWuInit;}
    public static String getuid(){
        return uid;
    }
    public static String getname(){
        return name;
    }
}
