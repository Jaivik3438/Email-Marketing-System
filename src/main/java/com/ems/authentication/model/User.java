package com.ems.authentication.model;

import com.ems.authentication.persistence.IUserPersistence;

import javax.servlet.http.HttpServletRequest;

public class User {

   public String userId;
    public String email;
    public String password;
    public String role;
    public String company;

User(HttpServletRequest request){
 this.email= (String)request.getAttribute("email");
 this.password= (String)request.getAttribute("password");
}
public User(){

}
    public User loadUser(IUserPersistence persistence) throws Exception{
    persistence.loadUser(this);
     return this;
    }
}
