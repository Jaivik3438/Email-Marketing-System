package com.ems.authentication.persistence;

import com.ems.authentication.model.User;

import java.sql.Connection;

public interface IUserPersistence {
    public User loadUser(User user) throws Exception;
}
