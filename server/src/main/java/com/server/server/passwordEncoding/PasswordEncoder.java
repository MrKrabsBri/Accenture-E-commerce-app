package com.server.server.passwordEncoding;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordEncoder {

    public final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=[]{}|;:',.<>?/";
    public final String NUMBERS = "0123456789";
    public final int MINIMUM_PASSWORD_LENGTH = 10;
    public final int MAXIMUM_PASSWORD_LENGTH = 30;

    /**
     * Checks if the password contains at least one special character.
     * 
     * @param password The password to check for a special character.
     * @return True if the password contains a special character, else throws an
     *         exception.
     * @throws IllegalArgumentException if the password does not contain a special
     *                                  character.
     */
    public boolean passwordContainsSpecialCharacter(String password) {
        boolean containsSpecialCharacter = false;

        for (char specialChar : SPECIAL_CHARACTERS.toCharArray()) {
            if (password.contains(String.valueOf(specialChar))) {
                containsSpecialCharacter = true;
                return containsSpecialCharacter;
            }
        }
        throw new IllegalArgumentException("Password does not contain a special character.");
    }

    /**
     * Checks if the password contains at least one number.
     * 
     * @param password The password to check for a number.
     * @return True if the password contains a number, else throws an exception.
     * @throws IllegalArgumentException if the password does not contain a number.
     */
    public boolean passwordContainsANumber(String password) {
        boolean containsNumber = false;

        for (char number : NUMBERS.toCharArray()) {
            if (password.contains(String.valueOf(number))) {
                containsNumber = true;
                return containsNumber;
            }
        }
        throw new IllegalArgumentException("Password does not contain a number.");
    }

    /**
     * Checks if the password length is within the specified range.
     * 
     * @param password The password to check for correct length.
     * @return True if the password length is correct, else throws an exception.
     * @throws IllegalArgumentException if the password length does not meet the
     *                                  criteria.
     */
    public boolean passwordLengthIsCorrect(String password) {
        boolean passwordLengthIsCorrect = false;
        passwordLengthIsCorrect = password.length() >= MINIMUM_PASSWORD_LENGTH
                && password.length() <= MAXIMUM_PASSWORD_LENGTH;
        if (!passwordLengthIsCorrect) {
            throw new IllegalArgumentException(
                    "Password must be at least " + MINIMUM_PASSWORD_LENGTH + " characters long and " +
                            "maximum " + MAXIMUM_PASSWORD_LENGTH + " symbols long");
        }
        return passwordLengthIsCorrect;

    }

    /**
     * Validates if the password meets the required conditions for security.
     * 
     * @param password The password to validate.
     * @return True if the password is secure, else throws an exception.
     * @throws IllegalArgumentException if the password does not meet the security
     *                                  criteria.
     */
    public boolean passwordIsSecure(String password) {

        if (passwordContainsSpecialCharacter(password) && passwordContainsANumber(password)
                && passwordLengthIsCorrect(password)) {
            return true;
        }
        throw new IllegalArgumentException("Password does not meet the required conditions");
    }

    /**
     * Hashes the password using SHA-256 algorithm if it meets the security
     * criteria.
     * 
     * @param password The password to hash.
     * @return The hashed password.
     * @throws RuntimeException         if the hashing algorithm (SHA-256) is not
     *                                  available.
     * @throws IllegalArgumentException if the password does not meet the security
     *                                  criteria.
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
     * @return True if the plain text password matches the hashed password, false
     *         otherwise.
     */
    public boolean verifyPassword(String password, String hashedPassword) {
        return hashPassword(password).equals(hashedPassword);
    }
}
