package com.ems.authentication.buisness;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 implements IHash {

    @Override
    public String hash(String plainString) throws NoSuchAlgorithmException {
        //code referred  from https://stackoverflow.com/questions/415953/how-can-i-generate-an-md5-hash-in-java
        //modifications are made to convert hex into string
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(plainString.getBytes());
        byte[] digest= md.digest();
        String hashedString = DatatypeConverter.printHexBinary(digest).toUpperCase();

        return hashedString;
    }
}
