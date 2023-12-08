package com.server.server.dtos;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.server.server.models.User;

/**
 * To convert between User and UserCreationDTO objects.
 */
@Component
public class UserCreationMapper {
    public UserCreationDTO toDto(User user) {
        UserCreationDTO dto = new UserCreationDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setUserType(user.getUserType());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public User fromDto(UserCreationDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setUserType(dto.getUserType());
        user.setEmail(dto.getEmail());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }
}
