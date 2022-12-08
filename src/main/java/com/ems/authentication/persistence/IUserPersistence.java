package com.ems.authentication.persistence;

import com.ems.authentication.Exception.UserAlreadyRegisteredException;
import com.ems.authentication.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface IUserPersistence {
    public User loadUser(User user) throws Exception;
    public boolean isUserRegistered(String email) throws SQLException;
    public User registerUser(User user);
}
