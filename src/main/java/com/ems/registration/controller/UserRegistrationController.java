package com.ems.registration.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.authentication.persistence.UserDB;
import com.ems.registration.business.IRegisterUser;
import com.ems.registration.business.RegisterUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ems.authentication.model.User;
import com.ems.registration.dto.RegisterUserDto;

import java.sql.SQLException;

@Controller()
@RequestMapping("/register")
public class UserRegistrationController{

    @PostMapping("/user")
    @ResponseBody
    public String register(@RequestBody RegisterUserDto registerUserDto) {
        try{
            IRegisterUser registerUser = new RegisterUser();
            boolean isUserRegistered = registerUser.registerUser(registerUserDto, new UserDB(MySqlPersistenceConnection.getInstance().getConnection()));
            return isUserRegistered ? "User registered successfully!" : "Error registering user";
        } catch (SQLException exception){
            return "Exception - class: UserRegistrationController method: register: " + exception.getMessage();
        }
    }

}
