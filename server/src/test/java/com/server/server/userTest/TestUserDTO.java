package com.server.server.userTest;

import com.server.server.dtos.UserCreationDTO;
import com.server.server.dtos.UserCreationMapper;
import com.server.server.dtos.UserDTO;
import com.server.server.dtos.UserMapper;
import com.server.server.enums.UserType;
import com.server.server.models.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestUserDTO {

    @DisplayName("Test UserDTO Getter and Setter Methods")
    @Test
    public void testUserDTOGetterSetter() {
        String username = "dummyUsername";
        UserType userType = UserType.ADMIN;
        String email = "dummy@email.com";

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setUserType(userType);
        userDTO.setEmail(email);

        assertEquals(username, userDTO.getUsername());
        assertEquals(userType, userDTO.getUserType());
        assertEquals(email, userDTO.getEmail());
    }

    @DisplayName("Test UserDTO Constructor")
    @Test
    public void testUserDTOConstructor() {
        int userId=1;
        String username = "dummyUsername";
        UserType userType = UserType.ADMIN;
        String email = "dummy@email.com";

        UserDTO userDTO = new UserDTO(userId,username, userType, email);

        assertEquals(userId, userDTO.getUserId());
        assertEquals(username, userDTO.getUsername());
        assertEquals(userType, userDTO.getUserType());
        assertEquals(email, userDTO.getEmail());
    }

    @DisplayName("Test User to UserDTO Conversion")
    @Test
    public void testUserToDtoConversion() {
        int userId = 1;
        String username = "dummyUser";
        String password = "HardOneToGuess123!";
        UserType userType = UserType.ADMIN;
        String email = "testing@mail.com";

        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(userType);
        user.setEmail(email);

        UserMapper userMapper = new UserMapper();
        UserDTO userDTO = userMapper.toDto(user);

        assertEquals(username, userDTO.getUsername());
        assertEquals(userType, userDTO.getUserType());
        assertEquals(email, userDTO.getEmail());
    }

    @DisplayName("Test UserDTO to User Conversion")
    @Test
    public void testDtoToUserConversion() {

        String username = "dummyUser";
        UserType userType = UserType.ADMIN;
        String email = "testing@mail.com";

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setUserType(userType);
        userDTO.setEmail(email);

        UserMapper userMapper = new UserMapper();
        User user = userMapper.fromDto(userDTO);

        assertEquals(username, user.getUsername());
        assertEquals(userType, user.getUserType());
        assertEquals(email, user.getEmail());
    }

    @DisplayName("Test UserCreationDTO Getter and Setter Methods")
    @Test
    public void testUserCreationDTOGetterSetter() {
        int userId=1;
        String username = "dummyUsername";
        String password = "HardOneToGuess123!";
        UserType userType = UserType.ADMIN;
        String email = "dummy@email.com";

        UserCreationDTO UserCreationDTO = new UserCreationDTO();
        UserCreationDTO.setUserId(userId);
        UserCreationDTO.setUsername(username);
        UserCreationDTO.setPassword(password);
        UserCreationDTO.setUserType(userType);
        UserCreationDTO.setEmail(email);

        assertEquals(userId, UserCreationDTO.getUserId());
        assertEquals(username, UserCreationDTO.getUsername());
        assertEquals(password, UserCreationDTO.getPassword());
        assertEquals(userType, UserCreationDTO.getUserType());
        assertEquals(email, UserCreationDTO.getEmail());
    }

    @DisplayName("Test UserCreationDTO Constructor")
    @Test
    public void testUserCreationDTOConstructor() {
        int userId=1;
        String username = "dummyUsername";
        String password = "HardOneToGuess123!";
        UserType userType = UserType.ADMIN;
        String email = "dummy@email.com";

        UserCreationDTO UserCreationDTO = new UserCreationDTO(userId, username, password, userType, email);

        assertEquals(userId, UserCreationDTO.getUserId());
        assertEquals(username, UserCreationDTO.getUsername());
        assertEquals(password, UserCreationDTO.getPassword());
        assertEquals(userType, UserCreationDTO.getUserType());
        assertEquals(email, UserCreationDTO.getEmail());
    }

    @DisplayName("Test User to UserCreationDTO Conversion")
    @Test
    public void testUserToUserCreationDTOConversion() {
        int userId = 1;
        String username = "dummyUser";
        String password = "HardOneToGuess123!";
        UserType userType = UserType.ADMIN;
        String email = "testing@mail.com";

        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(userType);
        user.setEmail(email);

        UserCreationMapper userCreationMapper = new UserCreationMapper();
        UserCreationDTO UserCreationDTO = userCreationMapper.toDto(user);

        assertEquals(userId, UserCreationDTO.getUserId());
        assertEquals(username, UserCreationDTO.getUsername());
        assertEquals(password, UserCreationDTO.getPassword());
        assertEquals(userType, UserCreationDTO.getUserType());
        assertEquals(email, UserCreationDTO.getEmail());
    }

    @DisplayName("Test UserCreationDTO to User Conversion")
    @Test
    public void testUserCreationDTOToUserConversion() {

        int userId = 1;
        String username = "dummyUser";
        String password = "HardOneToGuess123!";
        UserType userType = UserType.ADMIN;
        String email = "testing@mail.com";

        UserCreationDTO UserCreationDTO = new UserCreationDTO();
        UserCreationDTO.setUserId(userId);
        UserCreationDTO.setUsername(username);
        UserCreationDTO.setPassword(password);
        UserCreationDTO.setUserType(userType);
        UserCreationDTO.setEmail(email);

        UserCreationMapper userCreationMapper = new UserCreationMapper();
        User user = userCreationMapper.fromDto(UserCreationDTO);
        
        assertEquals(userId, user.getUserId());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(userType, user.getUserType());
        assertEquals(email, user.getEmail());
        
    }
}
