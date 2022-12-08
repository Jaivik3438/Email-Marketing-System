package com.ems.authentication.model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import com.ems.authentication.persistence.IUserPersistence;

import javax.servlet.http.HttpServletRequest;

public class User {
    public String userId;
    public String email;
    public String password;
    public Role role;
    public Company company;

    public User() {
    }

    public User(String email, String password) {
        this.userId = UUID.randomUUID().toString();
        this.email = email;
        this.password = generatePasswordHash(password);
    }

    public User loadUser(IUserPersistence persistence) throws Exception {
        persistence.loadUser(this);
        return new User();
    }

    public boolean isUserRegistered(String email) {
        // implement logic to call method from UserDB using IUserPersistence.
        return false;
    }

    public User registerUser(IUserPersistence persistence, String username, String password) {
        User newRegisteredUser = persistence.registerUser(new User(username, password));
        if (newRegisteredUser != null) {
            return newRegisteredUser;
        } else {
            return null;
        }
    }

    public String toString() {
        return "User : email " + email + "\t password: " + password;
    }

    // Reference: https://www.geeksforgeeks.org/sha-256-hash-in-java/
    private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    // Reference: https://www.geeksforgeeks.org/sha-256-hash-in-java/
    private static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public String generatePasswordHash(String password) {
        try {
            String passwordHash = toHexString(getSHA(password));
            return passwordHash;
        } catch (NoSuchAlgorithmException exception) {
            return "Error: Password Hashing " + exception.getMessage();
        }
        public User loadUser(IUserPersistence persistence) throws Exception{
            User user= persistence.loadUser(this);
            return user;
    }
        
}
