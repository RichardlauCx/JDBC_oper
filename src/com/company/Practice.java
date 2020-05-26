package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

import java.io.FileReader;
import java.lang.reflect.Field;

public class Practice {
    private static String simpleName;
    private static String name;

    public static void main(String[] args) {
        Object obj = new Test();
        Test test = new Test();
//        Class<?> c = obj.getClass();
        Class<?> c = obj.getClass();
//        Class<?> c = test.getClass();


        simpleName = c.getSimpleName();
        System.out.println(simpleName);  // 获取类名
//        name = c.getName();
//        System.out.println(name);  // 获取包含包名的类名


        Field[] fields = c.getDeclaredFields();
        for (Field field: fields) {
            System.out.println(field.getName());
        }

    }
}
