package com.server.server.dtos;

import com.server.server.enums.UserType;

public class UserCreationDTO {

    private int userId;
    private String username;
    private String password;
    private UserType userType;
    private String email;


    public UserCreationDTO() {
    }

    public UserCreationDTO(int userId, String username, String password, UserType userType, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.email = email;
    }

     /**
     * Gets the User ID.
     *
     * @return The User ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the User ID.
     *
     * @param userId The new User ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

     /**
     * Sets the password.
     *
     * @param password The new User password.
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Gets the username.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username The new username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user type.
     *
     * @return The user type.
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sets the user type.
     *
     * @param userType The new user type.
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Gets the user email.
     *
     * @return The user email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user email.
     *
     * @param email The new user email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}

