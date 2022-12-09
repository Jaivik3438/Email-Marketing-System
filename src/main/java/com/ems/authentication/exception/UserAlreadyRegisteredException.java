package com.ems.authentication.exception;

public class UserAlreadyRegisteredException extends Exception {
    public UserAlreadyRegisteredException() {
        super("User already registered");
    }

}
