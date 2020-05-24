package com.company.educoder;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package test;


public class News {
    private int id;
    private String title;
    private String author_name;

    public News() {
        super();
    }
    public News(int id, String title, String anthor_name) {
        super();
        this.id = id;
        this.title = title;
        this.author_name = anthor_name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor_name() {
        return author_name;
    }
    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
    @Override
    public String toString() {
        return "News [id=" + id + ", title=" + title + ", author_name="
                + author_name + "]";
    }


}

