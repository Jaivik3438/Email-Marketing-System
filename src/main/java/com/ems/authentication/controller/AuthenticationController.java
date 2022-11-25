package com.ems.authentication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.authentication.dto.RegisterUserDto;
import com.ems.authentication.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping("/authentication")
public class AuthenticationController {
    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) {

    }

    @PostMapping("/register")
    public String postMethodName(@RequestBody RegisterUserDto registerUserDto) {
        String email = registerUserDto.email;
        String password = registerUserDto.password;

        try{
            // Validate the input credentials by the user.
            if (email.isEmpty() || password.isEmpty()) {
                return "Invalid Credentials";
            } // validate is user already exists or not
            else if (User.) {
                return "User already exists";
            } else { // Create new user
                return "User registered successfully";
            }
        }

    }
}
