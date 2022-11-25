package com.ems.authentication.model;

import com.ems.authentication.persistence.IUserPersistence;
import com.ems.authentication.persistence.UserDB;

public class User {
    public String userId;
    public String email;
    public String password;
    public String role;
    public String company;

    public User loadUser(IUserPersistence persistence) throws Exception {
        persistence.loadUser(this);
        return new User();
    }

    public boolean isUserRegistered(String email) {
        // implement logic to call method from UserDB using IUserPersistence.
        return false;
    }
}
