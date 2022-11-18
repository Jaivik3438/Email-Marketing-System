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

        try {
            dbConn =MySqlPersistenceConnection.getInstance().getConnection();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return "all Access";


    }




}
