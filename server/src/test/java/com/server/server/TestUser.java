package com.server.server;

import com.server.server.enums.UserType;
import com.server.server.models.User;
import com.server.server.services.UserService;
import com.server.server.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUser {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;



    @Test
    @DisplayName("Create user should return created user")
    void testCreateUser() {

        User user = new User("unseenShadow", "asrQq52%monster", UserType.admin, "unseenRobert@gmail.com");

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User createdUser = userService.createUser("unseenShadow", "asrQq52%monster", UserType.admin, "unseenRobert@gmail.com");

        Assertions.assertNotNull(createdUser);
        Assertions.assertEquals("unseenShadow", createdUser.getUsername());
        Assertions.assertEquals("asrQq52%monster", createdUser.getPassword());
        Assertions.assertEquals(UserType.admin, createdUser.getUserType());
        Assertions.assertEquals("unseenRobert@gmail.com", createdUser.getEmail());
    }

    @Test
    @DisplayName("Get all users should return a list of users")
    void testGetAllUsers() {
        User user1 = new User("iAmUser1", "Xyahhtatalk12@", UserType.admin, "Kendrick@gmail.com");
        User user2 = new User("iAmUser2", "BVuyra;tlymss1", UserType.basic, "truth@outlook.com");
        User user3 = new User("iAmUser3", "Klktlkynva!100", UserType.basic, "testers@somekindofmail.com");
    
        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2, user3));
    
        List<User> users = userService.getAllUsers();
    
        Assertions.assertNotNull(users);
        Assertions.assertEquals(3, users.size());
        Assertions.assertEquals("iAmUser1", users.get(0).getUsername());
        Assertions.assertEquals("iAmUser2", users.get(1).getUsername());
        Assertions.assertEquals("iAmUser3", users.get(2).getUsername());
        Assertions.assertEquals("Xyahhtatalk12@", users.get(0).getPassword());
        Assertions.assertEquals("BVuyra;tlymss1", users.get(1).getPassword());
        Assertions.assertEquals("Klktlkynva!100", users.get(2).getPassword());
        Assertions.assertEquals(UserType.admin, users.get(0).getUserType());
        Assertions.assertEquals(UserType.basic, users.get(1).getUserType());
        Assertions.assertEquals(UserType.basic, users.get(2).getUserType());
        Assertions.assertEquals("Kendrick@gmail.com", users.get(0).getEmail());
        Assertions.assertEquals("truth@outlook.com", users.get(1).getEmail());
        Assertions.assertEquals("testers@somekindofmail.com", users.get(2).getEmail());
    }

    @Test
    @DisplayName("Get user by ID should return a user or empty")
    void testGetUserById() {
        int userId = 1;
        User user1 = new User("iAmUser1", "Xyahhtatalk12@", UserType.admin, "Kendrick@gmail.com");
        Optional<User> optionalUser = Optional.of(user1);

        Mockito.when(userRepository.findById(userId)).thenReturn(optionalUser);

        Optional<User> user = userService.getUserById(userId);

        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals("iAmUser1", user.get().getUsername());
        Assertions.assertEquals("Xyahhtatalk12@", user.get().getPassword());
        Assertions.assertEquals(UserType.admin, user.get().getUserType());
        Assertions.assertEquals("Kendrick@gmail.com", user.get().getEmail());
    }

    @Test
    @DisplayName("Update user should return updated user if found")
    void testUpdateUser() {
        int userIdToUpdate = 1;
        User existingUser = new User("oldUsername", "Xhajk10#$", UserType.basic, "old@email.com");
        User updatedUser = new User("newUsername", "LovelyC@t100", UserType.admin, "new@email.com");

        Mockito.when(userRepository.findById(userIdToUpdate)).thenReturn(java.util.Optional.of(existingUser));
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(userIdToUpdate, "newUsername", "LovelyC@t100", UserType.admin, "new@email.com");

        Assertions.assertNotNull(result);
        Assertions.assertEquals("newUsername", result.getUsername());
        Assertions.assertEquals("LovelyC@t100", result.getPassword());
        Assertions.assertEquals(UserType.admin, result.getUserType());
        Assertions.assertEquals("new@email.com", result.getEmail());
    }

    @Test
    @DisplayName("Delete user should return true if user exists and is deleted")
    void testDeleteUser() {
        int userIdToDelete = 1;
        User existingUser = new User("existingUser", "Ajhjtjt150!9aG", UserType.basic, "realEmail@gmail.com");

        Mockito.when(userRepository.findById(userIdToDelete)).thenReturn(java.util.Optional.of(existingUser));
        Mockito.doNothing().when(userRepository).deleteById(userIdToDelete);

        boolean deleted = userService.deleteUser(userIdToDelete);

        Assertions.assertTrue(deleted);
    }


}
