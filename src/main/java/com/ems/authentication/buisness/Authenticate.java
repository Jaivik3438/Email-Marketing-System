package com.ems.authentication.buisness;

import com.ems.authentication.model.User;
import com.ems.authentication.persistence.IUserPersistence;

import javax.servlet.http.HttpSession;


public class Authenticate implements IAuthenticate{


    @Override
    public State login(String email, String password, IUserPersistence userPersistence,IHash hashingAlgorithm) {
        User user= new User();
        user.email=email;
        try {
                user.password = hashingAlgorithm.hash(password);
                User returnedUser = user.loadUser(userPersistence);
                if (returnedUser==null){
                  return new UnSuccessfulLoginState().handle();
                }
                if (returnedUser.password.equalsIgnoreCase(user.password)){
                    return new SuccessfulLoggedInState(returnedUser).handle();
                }
                }
        catch (Exception e) {
                return new UnSuccessfulLoginState().handle();
        }
         return new UnSuccessfulLoginState().handle();
    }

    @Override
    public HttpSession logout(HttpSession session) {
        session.removeAttribute("isLoggedIn");
        session.removeAttribute("user");
        return session;
    }

}
