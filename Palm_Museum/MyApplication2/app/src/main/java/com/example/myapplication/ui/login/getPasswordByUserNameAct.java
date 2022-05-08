package com.example.myapplication.ui.login;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * 数据库工具类：连接数据库用、获取数据库数据用
 * 相关操作数据库的方法均可写在该类
 */
public class getPasswordByUserNameAct {

    private static String driver = "com.mysql.jdbc.Driver";// MySql驱动

//    private static String url = "jdbc:mysql://localhost:3306/map_designer_test_db";

    private static String user = String.valueOf(R.string.database_username);// 用户名

    private static String password = String.valueOf(R.string.database_password);// 密码

    private static Connection getConn(String dbName){

        Connection connection = null;
        try{
            Class.forName(driver);// 动态加载类
            String ip = String.valueOf(R.string.database_ip);// 写成本机地址，不能写成localhost，同时手机和电脑连接的网络必须是同一个

            // 尝试建立到给定数据库URL的连接
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/" + dbName,
                    user, password);

        }catch (Exception e){
            Log.e("connect","bad");
            e.printStackTrace();
        }

        return connection;
    }

    public static String getPasswordByUserName(String name){

        String password=new String("");
        // 根据数据库名称，建立连接
        Connection connection = getConn("museum");

        try {
            // mysql简单的查询语句。这里是根据MD_CHARGER表的NAME字段来查询某条记录
            String sql = "select password from user where name = ?";
//            String sql = "select * from MD_CHARGER";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){
                    // 设置上面的sql语句中的？的值为name
                    ps.setString(1, name);
                    // 执行sql查询语句并返回结果集
                    ResultSet rs = ps.executeQuery();
                    if (rs != null){
                        //int count = rs.getMetaData().getColumnCount();
                        //Log.e("DBUtils","列总数：" + count);
//                        while (rs.next()){
//                            // 注意：下标是从1开始的
//                            for (int i = 1;i <= count;i++){
//                                String field = rs.getMetaData().getColumnName(i);
//                                map.put(field, rs.getString(field));
//                            }
//                        }
                        password=rs.getMetaData().getCatalogName(1);
                        connection.close();
                        ps.close();
                        Log.e("password",password);
                        return  password;
                    }else {
                        return null;
                    }
                }else {
                    return  null;
                }
            }else {
                return  null;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("DBUtils","异常：" + e.getMessage());
            return null;
        }

    }

}