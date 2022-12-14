package com.ems.authentication.buisness;

import com.ems.authentication.model.User;

import javax.servlet.http.HttpSession;

public class HttpSessionManager implements ISessionManager{
    @Override
    public boolean validateSession(HttpSession session) {
        if(null==session.getAttribute("isLoggedIn")){
            return false;
        }
        return((boolean)session.getAttribute("isLoggedIn"));

    }

    @Override
    public User getUserFromSession(HttpSession session) {
        return (User)session.getAttribute("user");

    }
}
