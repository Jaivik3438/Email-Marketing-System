package com.ems.authentication.model;

import com.ems.authentication.persistence.IUserPersistence;

<<<<<<< .merge_file_RZxWoA
public class User {

=======
import javax.servlet.http.HttpServletRequest;

public class User {
>>>>>>> .merge_file_mynyJe
   public String userId;
    public String email;
    public String password;
    public String role;
    public String company;

<<<<<<< .merge_file_RZxWoA

    public User loadUser(IUserPersistence persistence) throws Exception{
    persistence.loadUser(this);
     return new User();
=======
User(HttpServletRequest request){
 this.email= (String)request.getAttribute("email");
 this.password= (String)request.getAttribute("password");
}
public User(){

}
    public User loadUser(IUserPersistence persistence) throws Exception{
    persistence.loadUser(this);
     return this;
>>>>>>> .merge_file_mynyJe
    }
}
