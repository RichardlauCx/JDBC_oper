//package com.company.educoder;
//
//// -*- coding: utf-8 -*-
////  @ Date   : 2019/5/20 13:14
////  @ Author : RichardLau_Cx
////  @ file   : Richard.Java
////  @ IDE    : IDEA
//
////package step2;
//
//import java.lang.reflect.Field;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class JDBCUtils {
//    private static Connection getConnection() {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        String url="jdbc:mysql://localhost:3306/mysql_db";
//        Connection conn=null;
//        try {
//            conn = DriverManager.getConnection(url, "root","123123");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
//    /**
//     * 类名对应表，属性对应字段
//     * @param obj　　传入的对象
//     * @return
//     */
//    public void insert(Object obj) {
//        Connection conn = getConnection();  //连接数据库
//        PreparedStatement ps = null;
//        /********** Begin **********/
//
//        String sql = null;
//        try {
//
//
//
//
//
//        }
//
//        /********** End **********/
//
//        catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            close(null,ps,conn);
//        }
//    }
//
//    /**
//     * 通过对象的Class获取对应表中的所有记录
//     * @param c
//     * @return
//     */
//    public <T> List<T> selectAll(Class<T> c) {
//        Connection conn = getConnection();
//        List<T> list = new ArrayList<T>();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        /********** Begin **********/
//        String sql = "";
//        try {
//
//
//
//
//
//            /********** End **********/
//
//        catch (Exception e) {
//                e.printStackTrace();
//            }finally {
//                close(rs,ps,conn);
//            }
//            return list;
//        }
//
//
//        /**
//         * 通过主键(默认第一个属性)删除对象
//         * @param obj
//         * @return
//         */
//        public void delete(Object obj) {
//            Connection conn = getConnection();
//            PreparedStatement ps = null;
//            /********** Begin **********/
//
//            String sql = null;
//            try {
//
//
//
//
//            }
//            /********** End **********/
//
//            catch (Exception e) {
//                e.printStackTrace();
//            }finally {
//                close(null,ps,conn);
//            }
//        }
//
//
//        /**
//         * 模拟jdbc的更新操作，默认第一个属性为主键
//         * @param obj
//         * @return
//         */
//        public void update(Object obj) {
//            Class<?> c = obj.getClass();//获取obj的Class
//            StringBuffer sb = new StringBuffer("update "+ c.getSimpleName() +" set ");//利用StringBuffer进行修改SQL语句的构造
//            Field[] field = c.getDeclaredFields();//通过反射获取对象的属性数组
//            for(int i = 1; i < field.length; i++) {
//                if(i != field.length-1) {    //判断是否为最后一个属性，若不是则后增加逗号
//                    sb.append(field[i].getName()).append("=?,");
//                }else {    //若为最后一个属性则添加 where
//                    sb.append(field[i].getName()).append("=? where ");
//                }
//            }
//            //默认第一个属性为主键，切更改时通过第一个属性进行更改
//            sb.append(field[0].getName() + "=?");
//            String sql = sb.toString()+";";
//            Connection conn = getConnection();//获取连接对象
//            PreparedStatement ps = null;
//            try {
//                ps = conn.prepareStatement(sql);
//                for(int i = 1; i < field.length; i++) {
//                    field[i].setAccessible(true);//设置可以访问私有属性
//                    ps.setObject(i, field[i].get(obj));//对预编译的SQL语句中的 ? 进行赋值
//                }
//                field[0].setAccessible(true);
//                ps.setObject(field.length, field[0].get(obj));
//                ps.execute();//执行sql语句
//            } catch (Exception e) {
//                e.printStackTrace();
//            }finally {
//                close(null,ps,conn);//关闭连接数据
//            }
//        }
//
//        public static void close(ResultSet rs,PreparedStatement ps,Connection conn){
//            try {
//                if(rs!=null) rs.close();
//                if(ps!=null) ps.close();
//                if(conn!=null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        public <T> Object selectById(Class<T> c,int id) {
//            String sql = "select * from "+ c.getSimpleName()+" where id="+id;
//            Field[] field = c.getDeclaredFields();
//            Connection conn = getConnection();
//            PreparedStatement ps = null;
//            ResultSet rs = null;
//            Object obj=null;
//            try {
//                ps = conn.prepareStatement(sql);
//                rs = ps.executeQuery();
//                obj = c.newInstance();
//                while(rs.next()) {
//                    for(int i = 0; i < field.length; i++) {
//                        field[i].setAccessible(true);
//                        field[i].set(obj, rs.getObject(field[i].getName()));
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }finally {
//                close(rs,ps,conn);
//            }
//            return obj;
//        }
//
//    }
