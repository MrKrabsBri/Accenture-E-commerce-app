package com.server.server.dtos;

import com.server.server.enums.UserType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserDTO {

    private int userId;
    private String username;
    private UserType userType;
    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@.{2,}\\..{2,}$", message = "Invalid email format")
    private String email;

    public UserDTO() {
    }

    public UserDTO(int userId, String username, UserType userType, String email) {
        this.userId = userId;
        this.username = username;
        this.userType = userType;
        this.email = email;
    }

    /**
     * Gets the user id.
     *
     * @return The user id.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the id.
     *
     * @param id The new id.
     */
    public void setUserId(int id) {
        this.userId = id;
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
