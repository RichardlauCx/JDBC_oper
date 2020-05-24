package com.company.educoder;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package jdbc;

//import java.sql.*;
//import java.sql.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class jdbcInsert {
    public static void insert(){
        /**********   Begin  **********/
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /**********   End   **********/



        Connection conn = null;
        PreparedStatement statement = null;
        /**********   Begin  **********/
        //连接并插入数据
        String url = "jdbc:mysql://127.0.0.1:3306/mysql_db";
        String user = "root";
        String password = "123123";

        try{
            conn = DriverManager.getConnection(url, user, password);
            String sql = "insert into student(id, name, sex, age) values(?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);

            statement.setInt(1, 1);
            statement.setString(2, "张三");
            statement.setString(3, "男");
            statement.setInt(4, 19);

            statement.setInt(1, 2);
            statement.setString(2, "李四");
            statement.setString(3, "女");
            statement.setInt(4, 18);

            statement.setInt(1, 3);
            statement.setString(2, "王五");
            statement.setString(3, "男");
            statement.setInt(4, 20);

            statement.executeUpdate();

            String sql_s = "select * from student";
            PreparedStatement pre_state = conn.prepareStatement(sql_s);
            ResultSet resultSet = pre_state.executeQuery();

            while (resultSet.next()) {
                System.out.format("%d %s %s %d", resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("sex"), resultSet.getInt("age"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**********   End   **********/
        finally {
            try {
                if (statement != null)
                    statement.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

