package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

public class Update {
    public static void main(String[] args) {
//                type();
        generics();
    }

    public static void type() {
        // 指定类型的JDBC工具调用
        JDBC_Utils_Type tool = new JDBC_Utils_Type();
        // 更改列
        String column_1 = "id";
        String value_1 = "7";

        // 条件列
        String column_2 = "column_1";
        String value_2 = "6";
        tool.upDate(column_1, value_1, column_2, value_2);
    }

    public static void generics() {
        // 泛型的JDBC工具调用
        table_1 table1 = new table_1();
        table1.setColumn_1(9);
        table1.setId(5);
        table1.setName("Marvel");
        table1.setSex("女");
        table1.setAge(666);

        JDBC_Utils_Generics tool_g = new JDBC_Utils_Generics();
        tool_g.upDate(table1);
    }
}
