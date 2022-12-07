package com.ems.companyDetails.persistence;

import com.ems.companyDetails.Exception.DatabaseNotFound;
import com.ems.companyDetails.model.companyDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class companyDetailsDB implements ICompanyDetailsPersistence{
    public Connection connection;

    public companyDetailsDB(Connection conn)
    {
        this.connection = conn;
    }
    @Override
    public companyDetails loadcompanyDetails(companyDetails company) throws Exception {
        if (connection == null){
            throw new DatabaseNotFound();
        }
        String sql = "select * from company_details";
        companyDetails resultCompany = null;
        try
        {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            resultCompany = new companyDetails();
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
}
