package com.ems.DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlPersistenceConnection {
    private static Connection conn;
    private static MySqlPersistenceConnection instance;

<<<<<<< .merge_file_fNyQF0
    private String mysqlurl="jdbc:mysql://localhost:3306/ems?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";

    private String password="rootroot";
    private String userName="root";
=======
    private String mysqlurl="jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_8_DEVINT?useSSL=false";

    private String password="wn3SKymKCd";
    private String userName="CSCI5308_8_DEVINT_USER";
>>>>>>> .merge_file_O1CG7E

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
<<<<<<< .merge_file_fNyQF0
        if (instance == null) {
            instance = new MySqlPersistenceConnection();
=======

        if (null == instance) {
            instance = new MySqlPersistenceConnection();
            System.out.println(instance);
>>>>>>> .merge_file_O1CG7E
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
