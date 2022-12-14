package com.ems.registration.business;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.authentication.model.User;
import com.ems.authentication.persistence.IUserPersistence;
import com.ems.authentication.persistence.UserDB;
import com.ems.registration.dto.RegisterUserDto;

import java.sql.Connection;

public class RegisterUser implements IRegisterUser{
    @Override
    public boolean registerUser(RegisterUserDto registerUserDto, IUserPersistence userDB) {
        String email = registerUserDto.email;
        String password = registerUserDto.password;

        // Validate the input credentials by the user.
        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Invalid Credentials");
            return false;
        }
        // validate is user already exists or not

        // Register User
       try{
           return userDB.registerUser(new User(email, password));
       } catch(Exception exception){
           System.out.println("Exception - class: RegisterUSer method: registerUser: " + exception.getMessage());
           return false;
       }
    }
}
