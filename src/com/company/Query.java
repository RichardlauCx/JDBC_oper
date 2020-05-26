package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA


import java.sql.SQLException;


public class Query {
    public static void main(String[] args) {
        JDBC_Utils_Type tool = new JDBC_Utils_Type();

        try {
                tool.findAll();

            } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
