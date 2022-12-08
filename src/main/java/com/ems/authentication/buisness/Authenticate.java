package com.ems.authentication.buisness;

import com.ems.authentication.model.User;
import com.ems.authentication.persistence.IUserPersistence;

import org.springframework.http.HttpCookie;

import javax.servlet.http.HttpSession;


public class Authenticate implements IAuthenticate{
    public Authenticate(){

    }

    @Override
    public State login(String email, String password, IUserPersistence userPersistence,IHash hashingAlgorithm) {
        User user= new User();
        user.email=email;
        try {
            user.password = hashingAlgorithm.hash(password);
            System.out.println(user.password);
            User returnedUser = user.loadUser(userPersistence);
        if (returnedUser.password.equalsIgnoreCase(user.password)){
            return new SuccessfulLoggedInState(returnedUser).handle();
        }
        } catch (Exception e) {
            return new UnSuccessfulLoginState().handle();
        }
         return new UnSuccessfulLoginState().handle();
    }

    @Override
    public HttpSession logout(HttpSession session) throws Exception {
        session.removeAttribute("isLogged");
        session.removeAttribute("user");
        return session;
    }

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
}
