package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

import java.sql.*;

public class Insert {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        Statement act = null;
        String url = "jdbc:mysql://127.0.0.1:3306/test?" +
                "useUnicode=true & characterEncoding=utf8 & serverTimezone=GMT%2B8 & useSSL=false";
        String user = "root";
        String password = "MySQL";

        try {
            conn = DriverManager.getConnection(url, user, password);
//            act = conn.createStatement();

            String sql_i = "insert into table_1(column_1, id, name, sex, age) values(?, ?, ?, ?, ?)";
            PreparedStatement pre_state = conn.prepareStatement(sql_i);
            pre_state.setInt(1, 7);  // 占位符索引从1开始
            pre_state.setInt(2, 8);  // 占位符索引从1开始
            pre_state.setString(3, "温宁远");
            pre_state.setString(4, "女");
            pre_state.setInt(5, 18);
            pre_state.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
