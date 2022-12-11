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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller()
@RequestMapping("/api/register")
public class UserRegistrationController{

    @PostMapping("/user")
    @ResponseBody
    public void register(HttpServletRequest request, HttpServletResponse response) {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.email = request.getParameter("email");
        registerUserDto.password = request.getParameter("password");

        try{
            IRegisterUser registerUser = new RegisterUser();
            boolean isUserRegistered = registerUser.registerUser(registerUserDto, new UserDB(MySqlPersistenceConnection.getInstance().getConnection()));
            String redirectUrl = isUserRegistered ? "/company-details" : "/register";
            response.sendRedirect(redirectUrl);
        } catch (SQLException exception){
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
