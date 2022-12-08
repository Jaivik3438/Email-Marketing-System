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
        String sql="select * from user,role,company_details where user.role_Id=role.role_Id and user.company_id=company_details.company_id and  email= ?";
        try {
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setString(1,user.email);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                User resultUser=new User();
                resultUser.userId=rs.getString("user_id");
                resultUser.email=rs.getString("email");
                resultUser.password=rs.getString("password");
                resultUser.role=rs.getString("role_name");
                resultUser.company=rs.getString("company_name");
                return resultUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
