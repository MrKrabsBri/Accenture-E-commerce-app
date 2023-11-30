package com.server.server.passwordEncoding;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordEncoder {

    public final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=[]{}|\\;:'\",.<>?/";
    public final String NUMBERS = "0123456789";

    /**
     * Checks if the password meets the security criteria:
     * - Contains at least one special character from the specified list
     * - Contains at least one number
     * - Length is between 8 and 30 characters
     *
     * @param password The password to check for security.
     * @return True if the password meets the security criteria, otherwise throws an exception.
     * @throws IllegalArgumentException if the password does not meet the required conditions.
     */
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
        boolean passwordLengthEight = password.length() >= 8 && password.length() <= 30;

        if (containsSpecialCharacter && containsNumber && passwordLengthEight) {
            return true;
        }

        throw new IllegalArgumentException("Password does not meet the required conditions");

    }

    /**
     * Hashes the password using the SHA-256 algorithm if it meets the security criteria.
     *
     * @param password The password to hash.
     * @return The hashed password.
     * @throws RuntimeException         if the hashing algorithm (SHA-256) is not available.
     * @throws IllegalArgumentException if the password does not meet the required conditions.
     */
    public String hashPassword(String password) {

        MessageDigest messageDigest;
        if (passwordIsSecure(password)) {
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] passwordBytes = password.getBytes();
                byte[] hashedBytes = messageDigest.digest(passwordBytes);
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

    /**
     * Verifies if a plain text password matches a hashed password.
     *
     * @param password       The plain text password to verify.
     * @param hashedPassword The hashed password stored in the database.
     * @return True if the plain text password matches the hashed password, false otherwise.
     */
    public boolean verifyPassword(String password, String hashedPassword) {
        System.out.println(password);
        return hashPassword(password).equals(hashedPassword);
    }
}
