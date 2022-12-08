package com.ems.authentication.model;

import com.ems.authentication.persistence.IUserPersistence;

import javax.servlet.http.HttpServletRequest;

public class User {
   public String userId;
    public String email;
    public String password;
    public String role;
    public String company;


public User(){

}
    public User loadUser(IUserPersistence persistence) throws Exception{
    User user= persistence.loadUser(this);
     return user;
    }
}
