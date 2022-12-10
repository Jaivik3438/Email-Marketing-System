package com.ems.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlPersistenceConnection {
    private static Connection conn;
    private static MySqlPersistenceConnection instance;

    private String mysqlurl="jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_8_DEVINT?useSSL=false";

    private String password="wn3SKymKCd";
    private String userName="CSCI5308_8_DEVINT_USER";

    private MySqlPersistenceConnection() throws SQLException {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn = DriverManager.getConnection(mysqlurl, userName, password);
    }

    public static MySqlPersistenceConnection getInstance() throws SQLException {

        if (null == instance) {
            instance = new MySqlPersistenceConnection();
            return instance;
        } else if (instance.getConnection().isClosed()) {
            instance = new MySqlPersistenceConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}
