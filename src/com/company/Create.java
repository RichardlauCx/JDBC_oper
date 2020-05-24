package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

import com.sun.corba.se.spi.ior.IORFactories;

import java.sql.*;

public class Create {

    public static void main(String[] args) {
	// write your code here
        try {  // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
//        String url = "jdbc:mysql://localhost:3306/student? useSSL=true";
        String url = "jdbc:mysql://localhost:3306/test?" +
                "useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
                // 此句话很重要，北京时间比格林威治时间晚八个小时
        String user = "root";
        String password = "MySQL";

        try {  // 建立连接
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement statement = null;
        try {  // 创建statement对象
            assert conn != null;
            statement = conn.createStatement();

            // 创建数据库
            String sql_1 = "drop database if exists test";
            String sql_2 = "create database test";

            // 执行并更新sql语句
            statement.executeUpdate(sql_1);
            statement.executeUpdate(sql_2);

            statement.executeUpdate("use test");  // 指定要操作的数据库
            String sql_3 = "create table table_1 (" +
                                "column_1 int not null," +
                                "id int," +
                                "name varchar(20)," +
                                "sex varchar(4)," +
                                "age int" +
                            ")";

            statement.executeUpdate(sql_3);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    // 尽量晚创建，早释放
                    conn.close();
                }

                if (statement != null) {
                    // 判断是否真的占用了资源
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
