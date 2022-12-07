package com.ems.authentication.controller;

import com.ems.authentication.model.User;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller()
@RequestMapping(value = "/authentication")
public class AuthenticationController {
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model){

        ModelAndView mav = new ModelAndView("index");
        User user = new User();
        user.email="test123";
        model.addAttribute("user", user);
        return "index";


    }
}
