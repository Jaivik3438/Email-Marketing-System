package com.ems.companyDetails.persistence;

import com.ems.companyDetails.Exception.DatabaseNotFound;
import com.ems.companyDetails.model.CompanyDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDetailsDB implements ICompanyDetailsPersistence{
    public Connection connection;

    public CompanyDetailsDB(Connection conn)
    {
        this.connection = conn;
    }
    @Override
    public List<CompanyDetails> loadcompanyDetails(CompanyDetails company) throws Exception {
        if (connection == null){
            throw new DatabaseNotFound();
        }
        String sql = "select * from company_details";
        List<CompanyDetails> resultCompany = new ArrayList<>();

        try
        {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                CompanyDetails companydetails = new CompanyDetails();
                companydetails.company_id = rs.getString("company_id");
                companydetails.company_name = rs.getString("company_name");
                companydetails.website_link = rs.getString("website_link");
                companydetails.company_email = rs.getString("company_email");
                companydetails.owner_name = rs.getString("owner_name");
                companydetails.facebook_link = rs.getString("facebook_link");
                companydetails.instagram_link = rs.getString("instagram_link");
                companydetails.twitter_url = rs.getString("twitter_link");
                System.out.println(companydetails);
                resultCompany.add(companydetails);

            }


        }

        catch (SQLException e)
        {
                e.printStackTrace();
        }
        return resultCompany;
    }

    @Override
    public int saveCompanyDetails(CompanyDetails saveCompany) throws Exception {
        try{
            Statement stmt = connection.createStatement();
            String saveCompanyQuery = "INSERT INTO company_details VALUES (" +
                    "\"" + saveCompany.getCompany_id() + "\", " +
                    "\"" + saveCompany.getCompany_name() + "\", " +
                    "\"" +saveCompany.getWebsite_link() + "\", " +
                    "\"" +saveCompany.getCompany_email() + "\", " +
                    "\"" +saveCompany.getOwner_name() + "\", " +
                    "\"" +saveCompany.getFacebook_link() + "\", " +
                    "\"" +saveCompany.getInstagram_link() + "\", "+
                    "\"" + saveCompany.getTwitter_url() + "\")";
            int numOfRowsInserted = stmt.executeUpdate(saveCompanyQuery);
            return  numOfRowsInserted;
        }
        catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }
}
