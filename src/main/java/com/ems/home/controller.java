package com.ems.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class controller {

    @RequestMapping("/")
    public String index(HttpSession session){
        System.out.println(session.getAttribute("isLogged"));
        return "index";
    }
}
