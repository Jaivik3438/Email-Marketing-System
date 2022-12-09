package com.ems.authentication.controller;

import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.authentication.buisness.Authenticate;
import com.ems.authentication.buisness.IAuthenticate;
import com.ems.authentication.buisness.State;
import com.ems.authentication.buisness.MD5;

import com.ems.authentication.persistence.UserDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

import com.ems.authentication.dto.RegisterUserDto;
import com.ems.authentication.model.User;

@Controller()
@RequestMapping("/authentication")
public class AuthenticationController {
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session){

        return "login";
    }
    @RequestMapping(value = "/login")

    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session){
        ModelAndView mv = new ModelAndView();

        try {
            Map parameters=request.getParameterMap();
            String email=((String[])parameters.get("email"))[0];
            String password=((String[])parameters.get("password"))[0];
            Connection conn= null;
            conn = MySqlPersistenceConnection.getInstance().getConnection();
            IAuthenticate authenticate=new Authenticate();
            State loginState=authenticate.login(email,password,new UserDB(conn),MD5.getInstance());
            mv.setViewName(loginState.redirectUrl);
            mv.addObject("message",(String)loginState.message);
            HttpSession session1 =request.getSession();
            loginState.handleSession(session1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return mv;
    }
    @RequestMapping(value = "/logout")

    public String logout(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {

        try {
            Connection conn = null;
            conn = MySqlPersistenceConnection.getInstance().getConnection();
            IAuthenticate authenticate = new Authenticate();
            authenticate.logout(request.getSession());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "logoutSuccessful";
    }

    @PostMapping("/register")
    @ResponseBody
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
