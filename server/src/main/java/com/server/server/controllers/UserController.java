package com.server.server.controllers;

import com.server.server.dtos.UserCreationDTO;
import com.server.server.dtos.UserCreationMapper;
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
    private final UserCreationMapper userCreationMapper;


    @Autowired
    public UserController(UserService userService, UserMapper userMapper, UserCreationMapper userCreationMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userCreationMapper = userCreationMapper;
    }

    /**
     * Endpoint to create a new user.
     *
     * @param userDTO The userDTO object containing details for creation.
     * @return The response entity containing the HTTP status.
     */
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreationDTO userCreationDTO) {
        User user = userCreationMapper.fromDto(userCreationDTO);
        userService.createUser(user.getUsername(), user.getPassword(), user.getUserType(), user.getEmail());
        return new ResponseEntity<>( HttpStatus.CREATED);
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
    public ResponseEntity<UserDTO> updateUser(@PathVariable int userId, @RequestBody UserCreationDTO UserCreationDTO) {
        User user = userCreationMapper.fromDto(UserCreationDTO);
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
    public ResponseEntity<UserDTO> verifyPassword(@RequestBody String userJSON) {

        UserDTO passwordMatches = userService.verifyUserPassword(userJSON);

        if (passwordMatches!=null) {
            return new ResponseEntity<>(passwordMatches, HttpStatus.OK);
        } else {
            UserDTO emptyObject = new UserDTO();
            return new ResponseEntity<>(emptyObject, HttpStatus.OK);
        }
    }

}
