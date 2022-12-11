package com.ems.companyDetails.controller;

import com.ems.authentication.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.*;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.companyDetails.model.CompanyDetails;
import com.ems.companyDetails.persistence.CompanyDetailsDB;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/company-details")
public class CompanyDetailsController {
    
    private CompanyDetails companyInformation = new CompanyDetails();

    @GetMapping("/getcompanys")
    public List<CompanyDetails> getCompanyDetails()
    {
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
    public void createCompanyTemplate (HttpServletRequest request, HttpSession session, HttpServletResponse response)
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
            User user = (User) session.getAttribute("user");
            session.setAttribute("companyId", addCompanyinformation.company_id);
            int responsefromUser = new CompanyDetailsDB(MySqlPersistenceConnection.getInstance().getConnection()).connectUserWithCompany(user.userId, addCompanyinformation.company_id);
            if(responsefromUser != -1){
                response.sendRedirect("/company-details");
            } else {
                response.sendRedirect("/add-company-details");
            }
        } catch(Exception exception){
//            return "Exception" + exception.getMessage();
        }
    }



}
