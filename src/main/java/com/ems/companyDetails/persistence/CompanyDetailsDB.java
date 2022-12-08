package com.ems.companyDetails.persistence;

import com.ems.companyDetails.Exception.DatabaseNotFound;
import com.ems.companyDetails.model.CompanyDetails;

import java.sql.*;

public class CompanyDetailsDB implements ICompanyDetailsPersistence{
    public Connection connection;

    public CompanyDetailsDB(Connection conn)
    {
        this.connection = conn;
    }
    @Override
    public CompanyDetails loadcompanyDetails(CompanyDetails company) throws Exception {
        if (connection == null){
            throw new DatabaseNotFound();
        }
        String sql = "select * from company_details";
        CompanyDetails resultCompany = null;
        try
        {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            resultCompany = new CompanyDetails();
            while (rs.next())
            {
                resultCompany.company_id = rs.getString("company_id");
                resultCompany.company_name = rs.getString("company_name");
                resultCompany.website_link = rs.getString("website_link");
                resultCompany.company_email = rs.getString("company_email");
                resultCompany.owner_name = rs.getString("owner_name");
                resultCompany.facebook_link = rs.getString("facebook_link");
                resultCompany.instagram_link = rs.getString("instagram_link");
                resultCompany.twitter_url = rs.getString("twitter_url");

            }

        }
        catch (SQLException e)
        {
            return null;
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
