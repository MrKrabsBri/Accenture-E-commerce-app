package com.server.server.dtos;

import org.springframework.stereotype.Component;

import com.server.server.models.User;

/**
 * To convert between User and UserDTO objects.
 */
@Component
public class UserMapper {
    public UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setUserType(user.getUserType());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User fromDto(UserDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setUserType(dto.getUserType());
        user.setEmail(dto.getEmail());
        return user;
    }
}

