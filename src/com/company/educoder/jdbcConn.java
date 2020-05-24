//package com.company.educoder;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package jdbc;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcConn {
    public static void getConn() {
        /**********    Begin   **********/
        try {
            //1.注册驱动

            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /**********    End   **********/


        /**********    Begin   **********/
        Connection conn = null;
        Statement statement = null;
        //2.建立连接并创建数据库和表
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "123123";

        try {
            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();

            String sql_1 = "drop database if exists test";
            String sql_2 = "create database test";

            statement.executeUpdate(sql_1);
            statement.executeUpdate(sql_2);

            statement.executeUpdate("use test");
            String sql_3 = "create table student(" +
                    "id int NOT NULL," +
                    "name varchar(20) ," +
                    "sex varchar(4) " +
                    "age int" +
                    ")";
            statement.executeUpdate(sql_3);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        /**********    End   **********/
        finally {  // 资源释放
            try {
                if(statement!=null)
                    statement.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

