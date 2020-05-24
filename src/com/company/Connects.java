package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connects {
    private static boolean flag;
//    boolean flag;

    Connects(boolean flag) {
        Connects.flag = flag;
    }

    Connects() {}


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Connection conn = null;
        String url = "jdbc:mysql://127.0.0.1:3306/test?" +
                "useUnicode=true & characterEncoding=utf8 & serverTimezone=GMT%2B8 & useSSL=false";
        String user = "root";
        String password = "MySQL";

        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(flag);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
