package com.ems.authentication.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("/authentication")
public class AuthenticationController {
    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response){


    }
}
