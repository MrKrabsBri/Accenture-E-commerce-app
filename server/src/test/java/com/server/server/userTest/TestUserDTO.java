// package com.server.server.userTest;

// import com.server.server.dtos.UserDTO;
// import com.server.server.dtos.UserMapper;
// import com.server.server.enums.UserType;
// import com.server.server.models.User;

// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// public class TestUserDTO {

//     @DisplayName("Test UserDTO Getter and Setter Methods")
//     @Test
//     public void testUserDTOGetterSetter() {
//         int userId = 1;
//         String username = "dummyUsername";
//         String password = "harqwedP@ssWord123";
//         UserType userType = UserType.ADMIN;
//         String email = "dummy@email.com";

//         UserDTO userDTO = new UserDTO();
//         userDTO.setUserId(userId);
//         userDTO.setUsername(username);
//         userDTO.setPassword(password);
//         userDTO.setUserType(userType);
//         userDTO.setEmail(email);

//         assertEquals(userId, userDTO.getUserId());
//         assertEquals(username, userDTO.getUsername());
//         assertEquals(password, userDTO.getPassword());
//         assertEquals(userType, userDTO.getUserType());
//         assertEquals(email, userDTO.getEmail());
//     }

//     @DisplayName("Test UserDTO Constructor")
//     @Test
//     public void testUserDTOConstructor() {
//         int userId = 1;
//         String username = "dummyUsername";
//         String password = "harqwedP@ssWord123";
//         UserType userType = UserType.ADMIN;
//         String email = "dummy@email.com";

//         UserDTO userDTO = new UserDTO(userId, username, password, userType, email);

//         assertEquals(userId, userDTO.getUserId());
//         assertEquals(username, userDTO.getUsername());
//         assertEquals(password, userDTO.getPassword());
//         assertEquals(userType, userDTO.getUserType());
//         assertEquals(email, userDTO.getEmail());
//     }

//     @DisplayName("Test User to UserDTO Conversion")
//     @Test
//     public void testUserToDtoConversion() {
//         int userId = 1;
//         String username = "dummyUser";
//         String password = "HardOneToGuess123!";
//         UserType userType = UserType.ADMIN;
//         String email = "testing@mail.com";

//         User user = new User();
//         user.setUserId(userId);
//         user.setUsername(username);
//         user.setPassword(password);
//         user.setUserType(userType);
//         user.setEmail(email);

//         UserMapper userMapper = new UserMapper();
//         UserDTO userDTO = userMapper.toDto(user);

//         assertEquals(userId, userDTO.getUserId());
//         assertEquals(username, userDTO.getUsername());
//         assertEquals(password, userDTO.getPassword());
//         assertEquals(userType, userDTO.getUserType());
//         assertEquals(email, userDTO.getEmail());
//     }

//     @DisplayName("Test UserDTO to User Conversion")
//     @Test
//     public void testDtoToUserConversion() {

//         int userId = 1;
//         String username = "dummyUser";
//         String password = "HardOneToGuess123!";
//         UserType userType = UserType.ADMIN;
//         String email = "testing@mail.com";

//         UserDTO userDTO = new UserDTO();
//         userDTO.setUserId(userId);
//         userDTO.setUsername(username);
//         userDTO.setPassword(password);
//         userDTO.setUserType(userType);
//         userDTO.setEmail(email);

//         UserMapper userMapper = new UserMapper();
//         User user = userMapper.fromDto(userDTO);

//         assertEquals(userId, user.getUserId());
//         assertEquals(username, user.getUsername());
//         assertEquals(password, user.getPassword());
//         assertEquals(userType, user.getUserType());
//         assertEquals(email, user.getEmail());
//     }

// }

