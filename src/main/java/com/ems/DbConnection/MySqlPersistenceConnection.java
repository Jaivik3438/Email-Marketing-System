package com.ems.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlPersistenceConnection {
    private static Connection conn;

    private MySqlPersistenceConnection(){

    }

    public static Connection getConnection(String DbUrl,String userName,String password) throws ClassNotFoundException, SQLException {
        if (conn!=null){
            return conn;
        }

        synchronized (conn){
            if (conn==null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(DbUrl, userName,password);
            }
            return conn;

        }


    }
}
