package com.itheima.util;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static Connection con;
//    private static String url="jdbc:mysql://localhost:3306/springboot";
//    private static String username="root";
//    private static String password="root";
    private static Properties properties;
    //    工具类私有空参构造
    private JDBCUtils() {
    }
//静态代码块只实例化一次
    static{



        try {
            Class.forName("com.mysql.jdbc.Driver");
            properties=new Properties();
            properties.load(new FileInputStream(new File("E:\\myproject\\day1913\\res\\my.properties")));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
}
//    静态方法返回连接对象
    public static Connection getConnection() throws SQLException {
       String url= properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        con= DriverManager.getConnection(url,username,password);
        return con;
    }
//    释放资源
    public static  void release(AutoCloseable ...c){
        if (c!=null){
            try {
                for (AutoCloseable closeable : c) {
                    closeable.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
