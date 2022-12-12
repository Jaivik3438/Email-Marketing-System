package com.ems.authentication.buisness;

import com.ems.authentication.model.User;

import javax.servlet.http.HttpSession;

public class HttpSessionManager implements ISessionManager{
    @Override
    public boolean validateSession(HttpSession session) {
        if(null==session.getAttribute("isLoggedIn")){
            return false;
        }
        if((boolean)session.getAttribute("isLoggedIn")){
            return true;
        }
        return false;
    }

    @Override
    public User getUserFromSession(HttpSession session) {
        User user= (User)session.getAttribute("user");
        return user;
    }
}
