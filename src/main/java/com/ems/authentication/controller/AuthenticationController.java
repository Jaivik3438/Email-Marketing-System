package com.ems.authentication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.authentication.dto.RegisterUserDto;
import com.ems.authentication.model.User;

@RestController()
@RequestMapping("/authentication")
public class AuthenticationController {
    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) {

    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterUserDto registerUserDto) {
        String email = registerUserDto.email;
        String password = registerUserDto.password;

        // Validate the input credentials by the user.
        if (email.isEmpty() || password.isEmpty()) {
            return "Invalid Credentials";
        }
        // validate is user already exists or not

        // Register User
        User newRegisteredUser = new User(email, password);

        return newRegisteredUser.toString();

    }
}
