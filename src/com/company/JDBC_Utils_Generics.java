package com.company;

// -*- coding: utf-8 -*-
//  @ Date   : 2019/5/20 13:14
//  @ Author : RichardLau_Cx
//  @ file   : Richard.Java
//  @ IDE    : IDEA

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JDBC_Utils_Generics {
    /**
     * 针对当前 jdbc:mysql://127.0.0.1:3306/test 数据库所实现的快速工具接口
     * 通过泛型来实现封装
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


    public void inserts(Test test) throws SQLException {
        // 添加操作
        PreparedStatement ps = null;
        String sql = "insert into test(column_1, id, name, sex, age)values(?,?,?)";
        Connection conn = getConn();

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, test.getColumn_1());
            ps.setInt(2, test.getId());
            ps.setString(3, test.getName());
            ps.setString(4, test.getSex());
            ps.setInt(5, test.getAge());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("添加数据失败");

        } finally {
            close(conn, ps, null, null);
        }
    }


    public List<Test> findAll() throws SQLException {
        /**
         * 查询所有数据
         * @return 查询到的以行为单位，以Test对象为元素的动态数组
         * @throws SQLException
         */

        int n = 17;  // 字符重复次数
        String temp = " ";  // 重复的字符
        String joint = String.join("", Collections.nCopies(n - 1, temp));
        PreparedStatement pre_state = null;
        ResultSet resultSet = null;
        Test test = null;
        List<Test> testList = new ArrayList<Test>();
        String sql = "select * from table_1";
        Connection conn = getConn();

        try {
            pre_state = conn.prepareStatement(sql);
            resultSet = pre_state.executeQuery();  // 将查询结果返回
//            System.out.println(resultSet);

            while (resultSet.next()) {
                // 循环获取每一行的数据，并以对象为元素存入动态数组中
                test = new Test();
                test.setColumn_1(resultSet.getInt(1));
                test.setId(resultSet.getInt(2));
                test.setName(resultSet.getString(3));
                test.setSex(resultSet.getString(4));
                test.setAge(resultSet.getInt(5));

                testList.add(test);
            }

            System.out.println("col" + joint + "id" + joint + "name" + joint + "gender" + joint + "age");
            for (Test rows: testList) {
                // 取出数据项
                System.out.print(rows.getColumn_1() + " ");
                System.out.print(rows.getId() + " ");
                System.out.print(rows.getName() + " ");
                System.out.print(rows.getSex() + " ");
                System.out.println(rows.getAge());
            }

            /*
            while (resultSet.next()) {
                // 循环判断表下一行是否还有数据（通过列的索引查询）
                int col = resultSet.getInt(1);
                int id = resultSet.getInt(2);
                String name = resultSet.getString(3);
                String gender = resultSet.getString(4);
                int age = resultSet.getInt(5);

                System.out.format("%d%20d%20s%20s%20d\n", col, id, name, gender, age);
            }
             */

            /*
            while (resultSet.next()) {
                // 循环判断表下一行是否还有数据（通过列名来查询）
                int col = resultSet.getInt("column_1");
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("sex");
                int age = resultSet.getInt("age");

                System.out.format("%d%20d%20s%20s%20d\n", col, id, name, gender, age);
            }

             */

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("查询所有数据失败");

        } finally {
            close(conn, pre_state, null, resultSet);
        }

        return testList;
    }


    public void upDate(Object obj) {
        /**
         * 更新操作
         * @param obj 用户传入与数据库中表结构字段名以及类型一致
         */

        PreparedStatement ps = null;
        Class<?> c = obj.getClass();  // 获取obj的类实例
        StringBuffer sql_sb = new StringBuffer("update " + c.getSimpleName() + " set ");  // 利用StringBuffer进行修改SQL语句的构造
        Field[] fields = c.getDeclaredFields();  // 通过反射获取对象的属性数组
        Connection con = getConn();

        for (int i=1; i < fields.length; i++) {
            if (i != fields.length-1) {
                // 判断是否为最后一个属性，若不是则后增加逗号
                sql_sb.append(fields[i].getName()).append("=?, ");  // 语句内容追加
            }
        }

            try {
                ps = con.prepareStatement(sql_sb);
                ps.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

    }


    public void deletes(int id) {
        // 删除操作（根据id值）
        Connection con = getConn();
        PreparedStatement ps = null;

        String sql = "delete from table_1 where id =" + id;

        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
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
