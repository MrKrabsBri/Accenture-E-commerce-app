package com.server.server.passwordEncoding;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordEncoder {
    //Check if password contains at least one special character
    //check if it contains at least one number
    //check if password length is 8 or more symbols
    public final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=[]{}|\\;:'\",.<>?/";
    public final String NUMBERS = "0123456789";

    public boolean passwordIsSecure(String password) {

        boolean containsSpecialCharacter = false;
        boolean containsNumber = false;


        for (char specialChar : SPECIAL_CHARACTERS.toCharArray()) {
            if (password.contains(String.valueOf(specialChar))) {
                containsSpecialCharacter = true;
                break;
            }
        }
        for (char number : NUMBERS.toCharArray()) {
            if (password.contains(String.valueOf(number))) {
                containsNumber = true;
                break;
            }
        }
        //check if password length is 8 or more symbols
        boolean passwordLengthEight = password.length() >= 8 && password.length() <= 30;

        if (containsSpecialCharacter && containsNumber && passwordLengthEight) {
            return true;
        }

        throw new IllegalArgumentException("Password does not meet the required conditions");

    }

    public String hashPassword(String password) {

        MessageDigest messageDigest;

        if (passwordIsSecure(password)) {
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
                // Convert the password string to bytes
                byte[] passwordBytes = password.getBytes();
                // Update the message digest with the password bytes
                byte[] hashedBytes = messageDigest.digest(passwordBytes);
                // Convert the hashed bytes to a hexadecimal representation
                StringBuilder stringBuilder = new StringBuilder();
                for (byte hashedByte : hashedBytes) {
                    stringBuilder.append(Integer.toString((hashedByte & 0xff) + 0x100, 16).substring(1));
                }

                return stringBuilder.toString();

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        throw new IllegalArgumentException("Password does not meet the required conditions");
    }
}
