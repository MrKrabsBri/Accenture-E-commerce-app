package com.server.server.dtos;

import com.server.server.enums.UserType;

public class UserDTO {
    private String username;
    private UserType userType;
    private String email;


    public UserDTO() {
    }

    public UserDTO(String username, UserType userType, String email) {
        this.username = username;
        this.userType = userType;
        this.email = email;
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

