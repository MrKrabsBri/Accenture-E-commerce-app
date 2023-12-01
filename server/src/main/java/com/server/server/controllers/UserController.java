package com.server.server.controllers;

import com.server.server.dtos.UserDTO;
import com.server.server.dtos.UserMapper;
import com.server.server.models.User;

import com.server.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Endpoint to create a new user.
     *
     * @param userDTO The userDTO object containing details for creation.
     * @return ResponseEntity<User> The response entity containing the created user and HTTP status.
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = userMapper.fromDto(userDTO);
        User createdUser = userService.createUser(user.getUsername(), user.getPassword(), user.getUserType(), user.getEmail());
        UserDTO createdUserDTO = userMapper.toDto(createdUser);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all users.
     *
     * @return ResponseEntity<List < UserDTO>> The response entity containing a list of users and HTTP status.
     */
     @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDTO> userDTOs = users.stream().map(userMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    /**
     * Endpoint to retrieve a user by ID.
     *
     * @param userId The unique identifier of the user.
     * @return ResponseEntity<UserDTO> The response entity containing the user and HTTP status.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(value -> {
            UserDTO userDTO = userMapper.toDto(value);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Endpoint to update a user by ID.
     *
     * @param userId The unique identifier of the user to be updated.
     * @param user   The updated user object containing new details.
     * @return ResponseEntity<UserDTO> The response entity containing the updated user and HTTP status.
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int userId, @RequestBody UserDTO userDTO) {
        User user = userMapper.fromDto(userDTO);
        User updatedUser = userService.updateUser(userId, user.getUsername(), user.getPassword(), user.getUserType(), user.getEmail());
        if (updatedUser != null) {
            UserDTO updatedUserDTO = userMapper.toDto(updatedUser);
            return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to delete a user by ID.
     *
     * @param userId The unique identifier of the user to be deleted.
     * @return ResponseEntity<Void> The response entity with HTTP status indicating success or failure.
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        boolean deleted = userService.deleteUser(userId);
        return deleted ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint to verify a user's password by ID.
     *
     * @param userJSON User credentials.
     * @return ResponseEntity<Boolean> The response entity containing true if the password matches, false otherwise, along with HTTP status.
     */
    @PostMapping("/verify-password")
    public ResponseEntity<Boolean> verifyPassword(@RequestBody String userJSON) {

        boolean passwordMatches = userService.verifyUserPassword(userJSON);

        if (passwordMatches) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }

}
