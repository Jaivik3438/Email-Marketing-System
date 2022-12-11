package com.ems.companyDetails.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.companyDetails.model.CompanyDetails;
import com.ems.companyDetails.persistence.CompanyDetailsDB;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/company-details")
public class CompanyDetailsController {
    
    private CompanyDetails companyInformation = new CompanyDetails();

    @GetMapping("/getcompanys")
    public List<CompanyDetails> getCompanyDetails(){
        try
        {

            return companyInformation.loadCompanyDetails(new CompanyDetailsDB(MySqlPersistenceConnection.getInstance().getConnection()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/save")
    public String createCompanyTemplate (HttpServletRequest request)
    {
        try{
            String companyName = request.getParameter("company_name");
            String website_link = request.getParameter("website_link");
            String company_email = request.getParameter("company_email");
            String owner_name = request.getParameter("owner_name");
            String facebook_link = request.getParameter("facebook_link");
            String instagram_link = request.getParameter("instagram_link");
            String twitter_url = request.getParameter("twitter_url");

            CompanyDetails addCompanyinformation = new CompanyDetails(companyName, website_link,company_email, owner_name, facebook_link, instagram_link, twitter_url);
            int responseCode = addCompanyinformation.saveCompanyDetails( new CompanyDetailsDB(MySqlPersistenceConnection.getInstance().getConnection()));
            if(responseCode == -1){
                return "Error in inserting company details";
            } else {
                return "Success: " + addCompanyinformation.toString();
            }
        } catch(Exception exception){
            return "Exception" + exception.getMessage();
        }
    }


}
