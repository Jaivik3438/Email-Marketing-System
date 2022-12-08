package com.ems.authentication.persistence;

import com.ems.authentication.exception.DatabaseNotFound;
import com.ems.authentication.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB implements IUserPersistence{

    public Connection connection;
    public UserDB(Connection conn){
        this.connection=conn;
    }

    public User loadUser( User user) throws Exception {
        if(connection==null){
            throw  new DatabaseNotFound();
        }
        String sql="select * from user,role,companydetails where user.roleId=role.roleId and user.company_id=company_details.company_id and  email= ?";
        try {
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setString(1,user.email);

            ResultSet rs = stmt.executeQuery();
            User resultUser=new User();
            while (rs.next()){
                user.userId=rs.getString("user_id");
                user.email=rs.getString("email");
                user.password=rs.getString("password");
                user.role=rs.getString("role");
                user.company=rs.getString("company_name");
            }
        } catch (SQLException e) {
            return null;
        }
        return user;
    }
}
