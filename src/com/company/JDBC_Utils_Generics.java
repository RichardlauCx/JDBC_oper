package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

import com.sun.xml.internal.bind.v2.TODO;
import jdk.internal.org.objectweb.asm.TypeReference;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JDBC_Utils_Generics {
    /**
     * 针对当前 jdbc:mysql://127.0.0.1:3306/test 数据库所实现的快速工具接口
     * 通过泛型来实现封装
     * 将实现只需传入对应的，和数据库中表结构字段名，以及类型一致的数据对象，调用接口方法即可实现对应操作
     */
    // 先放一些默认参数
    private String url = "jdbc:mysql://127.0.0.1:3306/test?" +
            "useUnicode=true & characterEncoding=utf8 & serverTimezone=GMT%2B8 & useSSL=false & allowPublicKeyRetrieval=true";
    private String user = "root";
    private String password = "MySQL";
    private Connection conn = null;
    boolean flag = true;

    public JDBC_Utils_Generics(String url_, String user_, String password_, boolean flag)
    {  // 构造方法
        url = url_;
        user = user_;
        password = password_;
        this.flag = flag;
    }

    public JDBC_Utils_Generics() {
        // 无参构造函数
    }


    private Connection getConn() {
        try {  // 建立连接
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(this.url, this.user, this.password);
            conn.setAutoCommit(this.flag);  // 是否开启自动提交SQL语句

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }


    public void operate(String sql) {
        // 操作
        PreparedStatement pre_state = null;
        Connection conn = getConn();

        try {
             pre_state = conn.prepareStatement(sql);

//            pre_state.setInt(1, 2);  // 占位符索引从1开始
//            pre_state.setInt(2, 3);  // 占位符索引从1开始
//            pre_state.setString(3, "Marvel");
//            pre_state.setString(4, "女");
//            pre_state.setInt(5, 26);

            pre_state.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, pre_state, null, null);
        }
    }


    public void inserts(Object obj) throws SQLException {
        // 添加操作
        PreparedStatement ps;
        Class<?> c = obj.getClass();
        StringBuilder sql_sb = new StringBuilder("insert into " + c.getSimpleName() + "(");  // test(column_1, id, name, sex, age)values(?,?,?)");
        Connection conn = getConn();

        Field[] fields = c.getDeclaredFields();
        for (int i=0; i < fields.length; i++) {
            if (i != fields.length-1) {
                sql_sb.append(fields[i].getName()).append(",");
            } else {
                sql_sb.append(fields[i].getName()).append(") ");
            }
        }

        sql_sb.append("values(");

        for (int i=0; i < fields.length; i++) {
            if (i != fields.length-1) {
                sql_sb.append("?,");
            } else {
                sql_sb.append("?)");
            }

        }

        ps = conn.prepareStatement(sql_sb.toString());

        try {

        for (int i=0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                ps.setObject(i+1, fields[i].get(obj));
            }

            ps.execute();

        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            throw new SQLException("添加数据失败");

        } finally {
            close(conn, ps, null, null);
        }
    }


    public <T> List<T> selectAll(Class<T> c) throws SQLException {
        /*
         * @param c
         * 通过对象的Class获取对应表中的所有记录
         * @return 查询到的以行为单位，以T对象为元素的动态数组
         * @throws SQLException
         */

        PreparedStatement pre_state = null;
        ResultSet resultSet = null;
        List<T> table1List = new ArrayList<>();
        // 这种泛型只需写在List<>里边即可。 List里边声明了泛型以后，再在ArrayList里边声明也重复冗余的。
        String sql = "select * from " + c.getSimpleName() + ";";
        Field[] fields = c.getDeclaredFields();
        Connection conn = getConn();

        try {
            pre_state = conn.prepareStatement(sql);
            resultSet = pre_state.executeQuery();  // 将查询结果返回
//            System.out.println(resultSet);

            while (resultSet.next()) {
                // 循环获取每一行的数据，并以对象为元素存入动态数组中
                T t_obj = c.newInstance();  // 通过反射，构造一个T类型的实例

                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(t_obj, resultSet.getObject(field.getName()));  // //设置obj对象的fields[i]属性的值
                }

                table1List.add(t_obj);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("查询所有数据失败");

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            close(conn, pre_state, null, resultSet);
        }

        return table1List;
    }


    public void upDate(Object obj) {
        /*
         * 更新操作
         * @param obj 用户传入与数据库中表结构字段名以及类型一致
         */

        PreparedStatement ps;
        Class<?> c = obj.getClass();  // 获取obj的类实例
        StringBuilder sql_sb = new StringBuilder("update " + c.getSimpleName() + " set ");
        // 利用StringBuilder进行修改SQL语句的构造，单线程进行，效率更高，但不具备线程安全
        Field[] fields = c.getDeclaredFields();  // 通过反射获取对象的属性数组
        Connection con = getConn();

        for (int i=1; i < fields.length; i++) {
            if (i != fields.length-1) {
                // 判断是否为最后一个属性，若不是则后增加逗号
                sql_sb.append(fields[i].getName()).append("=?, ");  // SQL语句内容追加
            }

            else {
                // 若为最后一个属性则添加where
                sql_sb.append(fields[i].getName()).append("=? where ");
            }
        }

        // 默认设置第二个属性为主键，且更改时通过第二个属性的值（id）进行修改
        sql_sb.append(fields[1].getName()).append("=?");

        try {
            ps = con.prepareStatement(sql_sb.toString());

            for (int i=1; i < fields.length; i++) {
                fields[i].setAccessible(true);  // 设置可以访问私有属性
                ps.setObject(i, fields[i].get(obj));  // 对于预编译的SQL语句中的？进行赋值
            }

            fields[1].setAccessible(true);
            ps.setObject(fields.length, fields[1].get(obj));  // 所以属性值设置完毕后，设置条件值
            ps.execute();  // 执行SQL语句

        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    public void deletes(Object obj) {
        /*
         * 通过主键(默认第二个属性)删除对象
         * @param obj
         * @return
         */

        PreparedStatement ps;
        Connection con = getConn();
        Class<?> c = obj.getClass();
        Field[] fields = c.getDeclaredFields();
        StringBuilder sql = new StringBuilder("delete from ");  // table_1 where id = + id);

        sql.append(c.getSimpleName()).append(" where ");
        fields[1].setAccessible(true);
        sql.append(fields[1].getName()).append(" = ");

        try {
            sql.append(fields[1].get(obj));
            ps = con.prepareStatement(sql.toString());
            ps.executeUpdate();

        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }

    }


    public void close (Connection con, PreparedStatement ps, Statement st, ResultSet resultSet) {
        // 关闭占用资源
        try {
            if (con != null) {
                con.close();
            }

            if (ps != null) {
                ps.close();
            }

            if (st != null) {
                st.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
