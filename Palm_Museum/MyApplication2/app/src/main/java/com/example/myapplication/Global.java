package com.example.myapplication;

import android.app.Application;

public class Global extends Application {
    public static String name;
    public static int wenWuInit=0;
    public static int genWuInit(){return wenWuInit;}
    public static String getName(){
        return name;
    }
}
