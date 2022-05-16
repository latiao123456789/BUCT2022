package com.example.myapplication;

import android.app.Application;

public class Global extends Application {
    public static String name;
    public static int wenWuInit=0;
    public static String wenWuUrl;
    public static int wenWuid;
    public static int genWuInit(){return wenWuInit;}
    public static String getName(){
        return name;
    }
    public static String getWenWuUrl(){return wenWuUrl;}
}
