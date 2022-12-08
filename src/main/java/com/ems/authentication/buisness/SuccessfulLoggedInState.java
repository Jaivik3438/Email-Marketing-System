package com.ems.authentication.buisness;

import com.ems.authentication.model.User;

import javax.servlet.http.HttpSession;

public class SuccessfulLoggedInState extends State{


    User user;
    SuccessfulLoggedInState(User user){
        super.message="login successful Welcome to EMS";
        super.redirectUrl="successfulLogin.html";
        this.user=user;
    }
    @Override
    public State handle() {
        return this;
    }

    @Override
    public HttpSession handleSession(HttpSession session) {

        session.setAttribute("isLoggedin",true);
        session.setAttribute("user",user);


        return session;
    }
}
