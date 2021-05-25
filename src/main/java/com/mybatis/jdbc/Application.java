package com.mybatis.jdbc;

import com.google.common.collect.Lists;
import com.mybatis.jdbc.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 导入JDBC驱动包
 * 通过DriverManager注册驱动
 * 创建连接
 * 创建statement
 * CRUD
 * 操作结果集
 * 关闭连接
 */
public class Application {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayList<User> userArrayList = Lists.newArrayList();
        /**
         * 加载Driver
         */
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("");
            /**
             * 执行一次sql语句
             */
            statement = connection.createStatement();

            resultSet=statement.executeQuery("select * from user");

            while (resultSet.next()){
                User user=new User();
                user.setId(resultSet.getInt(1));
                user.setAge(resultSet.getInt(2));
                user.setName(resultSet.getString(3));
                userArrayList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            if (statement!=null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
