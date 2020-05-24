package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

public class Update {
    public static void main(String[] args) {
        JDBC_Utils tool = new JDBC_Utils();
        String column = "sex";
        String value = "女";
        int id = 5;
        tool.upDate(column, value, id);
    }
}
