package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

public class Delete {
    public static void main(String[] args) {
        generics();
    }


    public static void generics() {
        // 泛型的JDBC工具调用
        table_1 table1 = new table_1();
        table1.setColumn_1(0);
        table1.setId(2);
        table1.setName("Marvel");
        table1.setSex("女");
        table1.setAge(666);

        JDBC_Utils_Generics tool_g = new JDBC_Utils_Generics();
        tool_g.deletes(table1);
    }


    public static void type() {
        // 指定类型的JDBC工具调用
        JDBC_Utils_Type tool = new JDBC_Utils_Type();
        tool.deletes(3);
    }
}
