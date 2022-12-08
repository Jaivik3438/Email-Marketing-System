package com.ems.companyDetails.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.companyDetails.model.CompanyDetails;
import com.ems.companyDetails.persistence.CompanyDetailsDB;

@RestController

public class CompanyDetailsController {
    
    private CompanyDetails companyInformation = new CompanyDetails();

    @GetMapping("/getcompanys")
    public CompanyDetails getCompanyDetails(){
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

    @PostMapping("/companyDetails")
    public String createCompanyTemplate (@RequestBody JsonNode body)
    {
        String companyName = body.get("company_name").asText();
        String website_link = body.get("website_link").asText();
        String company_email = body.get("company_email").asText();
        String owner_name = body.get("owner_name").asText();
        String facebook_link = body.get("facebook_link").asText();
        String instagram_link = body.get("instagram_link").asText();
        String twitter_url = body.get("twitter_url").asText();

        CompanyDetails addCompanyinformation = new CompanyDetails(companyName, website_link,company_email, owner_name, facebook_link, instagram_link, twitter_url);
        return addCompanyinformation.toString();
    }


}
