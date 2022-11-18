package com.ems.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlPersistenceConnection {
    private static Connection conn;
    private static MySqlPersistenceConnection instance;

    private String mysqlurl="jdbc:mysql://localhost:3306/ems?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";

    private String password="rootroot";
    private String userName="root";

    private MySqlPersistenceConnection() throws SQLException {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws SQLException, ClassNotFoundException {
        System.out.println(mysqlurl);
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn = DriverManager.getConnection(mysqlurl, userName, password);
    }

    public static MySqlPersistenceConnection getInstance() throws SQLException {
        if (instance == null) {
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
