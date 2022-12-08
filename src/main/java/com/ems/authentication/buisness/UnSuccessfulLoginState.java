package com.ems.authentication.buisness;

import javax.servlet.http.HttpSession;

public class UnSuccessfulLoginState extends State {

    UnSuccessfulLoginState(){
        super.message="login Unsuccessful please try again";
        super.redirectUrl="unsuccessfulLogin.html";
    }

    @Override
    public State handle() {
        return this;
    }

    @Override
    public HttpSession handleSession(HttpSession session) {

            session.removeAttribute("isLoggedIn");
            session.removeAttribute("user");
        return session;
    }
}
