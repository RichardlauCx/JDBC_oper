package com.company.educoder;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package jdbc;

import java.sql.*;

public class jdbcTransaction {

    public static void transaction(){
        try {
            Class.forName("com.mysql.jdbc.Driver" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement ps = null;
        /**********  Begin   **********/
        //连接数据库并开启事务
        String url = "jdbc:mysql://127.0.0.1:3306/mysql_db";
        String user = "root";
        String password = "123123";

        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);

            String sql = "insert into student(id, name, sex, age) values(?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 4);
            ps.setString(2, "赵六");
            ps.setString(3, "女");
            ps.setInt(4, 21);

            ps.executeUpdate();
//            conn.commit();

            ps.executeUpdate("update student id,name");
            conn.commit();

        } catch (SQLException e) {
            try {
                //事务回滚
                assert conn != null;
                conn.rollback();

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        /**********  End   **********/
        finally {
            try {
                if(ps!=null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
