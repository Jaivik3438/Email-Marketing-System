package com.ems.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController()
public class AccessController {


    @GetMapping("/access")
    public String index()  {
        Connection dbConn=null;
        String url="jdbc:mysql://localhost:3306/ems?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";

        String password="rootroot";
        String userName="root";
        try {
            dbConn =MySqlPersistenceConnection.getConnection(url,userName,password);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());


        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }


        return "all Access";


    }




}
