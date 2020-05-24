package com.company.educoder;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package step1;

import java.sql.*;

public class QueryPass {

    // 查询数据代码不用上实验报告
    public static void queryDB() {

        /********* Begin *********/

        // 第一步：加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 第二步：建立连接, "root"和"123123"是针对MySQL设置了用户名(root)和密码(123123)的情况
        // 127.0.0.1:3306是mysql服务器地址及端口   数据库编码格式设置为utf-8
        String url = "jdbc:mysql://127.0.0.1:3306/tsgc? characterEncoding=utf-8";
        String user = "root";
        String password = "123123";

        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 第三步：建立statement对象
        PreparedStatement ps = null;

        // 第四步：查询数据
        String sql = "select * from employee";

        try {
            try {
                assert con != null;
                ps = con.prepareStatement(sql);
                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    int no = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    int pd = resultSet.getInt(3);
                    String sex = resultSet.getString(4);
                    float salary = resultSet.getFloat(5);
                    System.out.println("no:" + no + "\tname:" + name + "\tpassword:" + pd + "\tsex:" + sex + "\tsalary:" + salary);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            // 第五步：关闭statement对象和连接对象
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        /********* End *********/
    }
}

