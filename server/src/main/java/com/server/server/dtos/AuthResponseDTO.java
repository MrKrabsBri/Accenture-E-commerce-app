package com.server.server.dtos;

public class AuthResponseDTO {
    private UserDTO userDTO;
    private String jwtToken;

    // Constructors, getters, and setters

    public AuthResponseDTO(UserDTO userDTO, String jwtToken) {
        this.userDTO = userDTO;
        this.jwtToken = jwtToken;
    }

    // Getters and Setters

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}