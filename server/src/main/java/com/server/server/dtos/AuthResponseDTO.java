package com.server.server.dtos;

/**
 * DTO class for authentication response.
 * Contains userDTO and jwtToken.
 * Used for returning the response to the client.
 **/
public class AuthResponseDTO {
    private UserDTO userDTO;
    private String jwtToken;

    public AuthResponseDTO(UserDTO userDTO, String jwtToken) {
        this.userDTO = userDTO;
        this.jwtToken = jwtToken;
    }

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