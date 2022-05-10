package com.example.myapplication.DB;

import java.sql.*;

public class DBConnect {
    public static String DRIVER_CLASS_NAME="com.mysql.jdbc.Driver";
    public static String url = "jdbc:mysql://192.168.31.22:3306/museum?useUnicode=true&characterEncoding=UTF-8";
    public static String user = "root";
    public static String password = "root";

    public static Connection GetConnection()  {
        Connection conn = null;
        try {
            Class.forName(DRIVER_CLASS_NAME);
            conn = (Connection)DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();;
        }
        return conn;
    }
}
