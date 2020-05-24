package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

public class Test {
    private int column_1;
    private int id;
    private String name;
    private String sex;
    private int age;

    public Test(int column_1, int id, String name, String sex, int age) {
        this.column_1 = column_1;
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Test() {
    }

    public int getColumn_1() {
        return column_1;
    }

    public void setColumn_1(int column_1) {
        this.column_1 = column_1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Test{" +
                "column_1=" + column_1 +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                '}';
    }
}
