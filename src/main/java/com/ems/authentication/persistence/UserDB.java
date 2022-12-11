package com.ems.authentication.persistence;

import com.ems.authentication.exception.DatabaseNotFound;
import com.ems.registration.exception.UserAlreadyRegisteredException;
import com.ems.authentication.model.Company;
import com.ems.authentication.model.Role;
import com.ems.authentication.model.User;
import com.ems.registration.model.UserSegment;

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

    public boolean registerUser(User user) {

        if (connection != null) {
            String registerUserQuery = "INSERT INTO user(user_id, created_date, email, password, role_id) values(?,?,?,?,?)";

            try {
                PreparedStatement registerUserStatement = connection.prepareStatement(registerUserQuery);
                registerUserStatement.setString(1, user.userId);
                Date currentDate = new Date();
                Timestamp currentTimeStamp = new java.sql.Timestamp(currentDate.getTime());
                registerUserStatement.setTimestamp(2, currentTimeStamp);
                registerUserStatement.setString(3, user.email);
                registerUserStatement.setString(4, user.password);
                registerUserStatement.setInt(5, 1);

                int userRegistrationStatus = registerUserStatement.executeUpdate();

                if(userRegistrationStatus != -1){
                    return createUserSegment(user.userId);
                }

            } catch (SQLException exception) {
                System.out.println("Exception: Class: UserDB method: register" + exception.getMessage());
                printSQLException(exception);
                return false;
            }

        }
        return false;
    }

    public boolean createUserSegment(String userId){
        if(connection != null){
            String registerUserQuery = "INSERT INTO user_segment(user_segment_id, application_name, api_key, user_id) values(?,?,?,?)";

            try {
                PreparedStatement createUserSegmentStatement = connection.prepareStatement(registerUserQuery);
                UserSegment newUserSegment = new UserSegment(userId);
                createUserSegmentStatement.setString(1, newUserSegment.userSegmentId);
                createUserSegmentStatement.setString(2, newUserSegment.applicationName);
                createUserSegmentStatement.setString(3, newUserSegment.apiKey);
                createUserSegmentStatement.setString(4, newUserSegment.userId);

                int userRegistrationStatus = createUserSegmentStatement.executeUpdate();

                if(userRegistrationStatus == -1){
                    return false;
                } else {
                    return true;
                }

            } catch (SQLException exception) {
                System.out.println("Exception: Class: UserDB method: register" + exception.getMessage());
                printSQLException(exception);
                return false;
            }
        }
           return false;
    }

    public User getUserByEmail(String email){
        if (connection != null) {
            String sql = "select * from user where email= ?";
            try {
                User user = new User();
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, email);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    user.userId = rs.getString("user_id");
                    user.email = rs.getString("email");
                    user.role.roleId = String.valueOf(rs.getInt("role_id"));
                }

                return user;

            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }
        return null;
    }
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
