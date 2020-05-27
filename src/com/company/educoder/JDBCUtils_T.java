package com.company.educoder;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

//package step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import test.News;

public class JDBCUtils_T {
    /**
     * 连接数据库
     */
    private static Connection getConnection() {
        Connection conn=null;
        String url="jdbc:mysql://127.0.0.1:3306/mysql_db";
        String user = "root";
        String password = "123123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);


        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public void update(News news) throws SQLException {
        /*
         * 更新数据方法
         * @param news
         * @throws SQLException
         */

        Connection conn = getConnection();
        PreparedStatement ps = null;
        String sql = "update news set author_name='光明网' where id=1";
        try{
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
            throw new SQLException("更新数据失败");
        }finally{
            close(null, ps, conn);
        }
    }


    public List<News> findAll() throws SQLException {
        /*
         * 查询所有数据
         * @return newsList
         * @throws SQLException
         */
        Connection conn =  getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        News news;
        List<News> newsList = new ArrayList<>();
        String sql = "select * from news";
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                news = new News();
                news.setId(rs.getInt(1));
                news.setTitle(rs.getString(2));
                news.setAuthor_name(rs.getString(3));

                newsList.add(news);
            }

        }catch(SQLException e){
            e.printStackTrace();
            throw new SQLException("查询所有数据失败");
        }finally{
            close(rs, ps, conn);
        }
        return newsList;
    }


    public void delete(int id) throws SQLException{
        /*
         * 删除方法
         * @param id
         * @throws SQLException
         */
        Connection conn = getConnection();
        PreparedStatement ps = null;

        String sql = "delete from news where id=" + id;
        try{
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
            throw new SQLException(" 删除数据失败");
        }
        finally{
            close(null, ps, conn);
        }
    }


    public void insert(News news) throws SQLException {
        /*
          增加对象
          @param news
         * @throws SQLException
         */
        Connection conn = getConnection();
        PreparedStatement ps = null;
        String sql = "insert into news(id,title,author_name)values(?,?,?)";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, news.getId());
            ps.setString(2, news.getTitle());
            ps.setString(3, news.getAuthor_name());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            throw new SQLException("添加数据失败");
        }finally{
            close(null, ps, conn);
        }
    }

    /**
     * 根据id查询对象
     * @param id s
     * @return s
     * @throws SQLException s
     */
    public News findById(int id) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        News news = null;
        String sql = "select * from news where id=?";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                news = new News();
                news.setId(id);
                news.setTitle(rs.getString(2));
                news.setAuthor_name(rs.getString(3));
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new SQLException("根据ID查询数据失败");
        }
        finally{
            close(rs, ps, conn);
        }
        return news;
    }

    /**
     * 关闭数据库连接
     * @param rs d
     * @param ps d
     * @param conn d
     */
    public static void close(ResultSet rs,PreparedStatement ps,Connection conn){

        try {
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            if(conn!=null)conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

