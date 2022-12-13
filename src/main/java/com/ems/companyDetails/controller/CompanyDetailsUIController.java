package com.ems.companyDetails.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.authentication.model.User;
import com.ems.companyDetails.model.CompanyDetails;
import com.ems.companyDetails.persistence.CompanyDetailsDB;
import com.ems.companyDetails.persistence.ICompanyDetailsPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CompanyDetailsUIController {
    @GetMapping("/add-company-details")
    public String getCompanyaDetails() {
        return "companyDetailsForm";
    }

    @GetMapping("/company-details")
    public String companyDetails(Model model, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        String companyId = (String) session.getAttribute("companyId");
        System.out.println(companyId);
        CompanyDetails companyDetails = getCompanyDetailsByUserId(user.userId);
        model.addAttribute("companyDetails", companyDetails);
        return "companyDetails";
    }

    public CompanyDetails getCompanyDetailsByUserId(String userId) throws Exception {
        //CompanyDetailsDB companyDetailsPersistence = new CompanyDetailsDB(MySqlPersistenceConnection.getInstance().getConnection());
        //return companyDetailsPersistence.getCompanyByUserId(userId);
        CompanyDetailsDB dataBaseObj = new CompanyDetailsDB(MySqlPersistenceConnection.getInstance().getConnection());
        CompanyDetails companyDetailsByUserId = new CompanyDetails();
       return companyDetailsByUserId.getCompanyDetailsByUserId(userId,dataBaseObj);
    }

}