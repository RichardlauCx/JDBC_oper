package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

public class Update {
    public static void main(String[] args) {
        JDBC_Utils_Type tool = new JDBC_Utils_Type();
        // 更改列
        String column_1 = "id";
        String value_1 = "7";

        // 条件列
        String column_2 = "column_1";
        String value_2 = "6";
        tool.upDate(column_1, value_1, column_2, value_2);
    }
}
