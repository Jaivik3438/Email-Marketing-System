package com.ems.authentication.persistence;

import com.ems.authentication.exception.DatabaseNotFound;
import com.ems.authentication.exception.UserAlreadyRegisteredException;
import com.ems.authentication.model.Company;
import com.ems.authentication.model.Role;
import com.ems.authentication.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class UserDB implements IUserPersistence {

    public Connection connection;
    public UserDB(Connection conn){
        this.connection=conn;
    }

    public User loadUser(User user) throws Exception {
        if (connection == null) {
            throw new DatabaseNotFound();
        }
        String sql="select * from user,role,company_details where user.role_Id=role.role_Id and user.company_id=company_details.company_id and  email= ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.email);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                User resultUser=new User();
                resultUser.userId=rs.getString("user_id");
                resultUser.email=rs.getString("email");
                resultUser.password=rs.getString("password");
                Role role=new Role();
                role.name=rs.getString("role_name");
                resultUser.role=role;
                Company company = new Company();
                company.name=rs.getString("company_name");
                company.companyId=rs.getString("company_Id");
                resultUser.company=company;
                return resultUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public boolean isUserRegistered(String email) throws SQLException {
        if (connection != null) {
            String sql = "select user_id from user where email= ?";
            try {
                String userId = null;
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, email);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    userId = rs.getString("user_id");
                }

                if (userId.length() > 0) {
                    throw new UserAlreadyRegisteredException();
                }

            } catch (UserAlreadyRegisteredException exception) {
                return true;
            }
        }
        return false;
    }

    public User registerUser(User user) {

        if (connection != null) {
            String registerUserQuery = "INSERT INTO user(user_id, created_date, email, password, role_id, company_id) values(?,?,?,?,?,?)";

            try {
                PreparedStatement registerUserStatement = connection.prepareStatement(registerUserQuery);
                registerUserStatement.setString(1, user.userId);
                Date currentDate = new Date();
                Timestamp currentTimeStamp = new java.sql.Timestamp(currentDate.getTime());
                registerUserStatement.setTimestamp(2, currentTimeStamp);
                registerUserStatement.setString(3, user.email);
                registerUserStatement.setString(4, user.password);
                registerUserStatement.setString(5, user.role.roleId);
                registerUserStatement.setString(6, user.company.companyId);

                ResultSet resultSet = registerUserStatement.executeQuery();

                User newRegisteredUser = new User();
                while (resultSet.next()) {
                    newRegisteredUser.userId = resultSet.getString("user_id");
                    newRegisteredUser.email = resultSet.getString("email");
                    newRegisteredUser.password = resultSet.getString("password");
                    newRegisteredUser.role.roleId = resultSet.getString("role");
                    newRegisteredUser.company.companyId = resultSet.getString("company_name");
                }

                return newRegisteredUser;

            } catch (SQLException exception) {
                return null;
            }

        }

        return null;
    }

}
