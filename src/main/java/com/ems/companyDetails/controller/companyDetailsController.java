package com.ems.companyDetails.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.companyDetails.model.companyDetails;
import com.ems.companyDetails.persistence.companyDetailsDB;

@RestController
public class companyDetailsController {
    
    private companyDetails companyInformation = new companyDetails();

    @GetMapping("/getcompanys")
    public companyDetails getCompanyDetails(){
        try {
            return companyInformation.loadCompanyDetails(new companyDetailsDB(MySqlPersistenceConnection.getInstance().getConnection()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
