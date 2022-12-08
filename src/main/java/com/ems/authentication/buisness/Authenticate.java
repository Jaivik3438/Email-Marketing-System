package com.ems.authentication.buisness;

import com.ems.authentication.model.User;
import com.ems.authentication.persistence.IUserPersistence;

import org.springframework.http.HttpCookie;

import javax.servlet.http.HttpSession;


public class Authenticate implements IAuthenticate{
    public Authenticate(IUserPersistence userPersistence,IHash hashingAlgorithm){
        this.userPersistence=userPersistence;
        this.hashingAlgorithm=hashingAlgorithm;
    }
    public IUserPersistence userPersistence;

    public IHash hashingAlgorithm;
    @Override
    public State login(String email, String password) {
        User user= new User();
        user.email=email;
        try {
            user.password = hashingAlgorithm.hash(password);
            System.out.println(user.password);
            User returnedUser = user.loadUser(userPersistence);
            System.out.println(returnedUser);
        if (returnedUser.password.equals(user.password)){
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
}
