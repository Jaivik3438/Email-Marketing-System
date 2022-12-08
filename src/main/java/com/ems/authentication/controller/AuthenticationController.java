package com.ems.authentication.controller;

<<<<<<< .merge_file_OLS57b
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("/authentication")
public class AuthenticationController {
    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response){


=======
import com.ems.DbConnection.MySqlPersistenceConnection;
import com.ems.authentication.buisness.Authenticate;
import com.ems.authentication.buisness.IAuthenticate;
import com.ems.authentication.buisness.State;
import com.ems.authentication.buisness.MD5;

import com.ems.authentication.persistence.UserDB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Controller()
@RequestMapping(value = "/authentication")
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
            IAuthenticate authenticate=new Authenticate(new UserDB(conn),new MD5());
            State loginState=authenticate.login(email,password);
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
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session){



        try {

            Connection conn= null;
            conn = MySqlPersistenceConnection.getInstance().getConnection();
            IAuthenticate authenticate=new Authenticate(new UserDB(conn),new MD5());
            authenticate.logout(request.getSession());

        } catch (Exception e) {
            e.printStackTrace();
        }




        return "logoutSuccesfull";
>>>>>>> .merge_file_1Bd7lC
    }
}
