package com.company.educoder;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package step1;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class UpdatePass {
    // 修改数据
    public static void updateDB() {

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
        String sql = "update employee set password='hello' where sex='女'";

        // 第四步：修改数据
        assert con != null;
        try {

            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 第五步：关闭statement对象和连接对象
        try {
            if (con != null) {
                con.close();
            }

            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        /********* End *********/
    }

}

