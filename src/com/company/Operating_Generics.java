package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

import java.sql.SQLException;

public class Operating_Generics {
    // 针对JDBC泛型接口工具的便捷操作

    public static void main(String[] args) {
        JDBC_Utils_Generics tool = new JDBC_Utils_Generics();
        table_1 table1 = new table_1();

//        selectAll(tool, table1);
        inserts(tool, table1);
    }

    public static void selectAll(JDBC_Utils_Generics tool, table_1 table1) {
        try {
            System.out.println(tool.selectAll(table1.getClass()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void inserts(JDBC_Utils_Generics tool, table_1 table1) {
        try {
            tool.inserts(table1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
