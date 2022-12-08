package com.ems.authentication.buisness;


import org.springframework.http.HttpCookie;

import javax.servlet.http.HttpSession;

public interface IAuthenticate {
    public State login(String email, String password);
    public HttpSession logout(HttpSession session) throws Exception;
}
