package com.server.server.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.server.dtos.UserDTO;
import com.server.server.dtos.UserMapper;
import com.server.server.enums.UserType;
import com.server.server.models.User;
import com.server.server.passwordEncoding.PasswordEncoder;
import com.server.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    protected static final Logger logger = LogManager.getLogger();
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    /**
     * Create a new user.
     *
     * @param username The username of the new user.
     * @param password The password of the new user.
     * @param userType The type of the new user.
     * @param email    The email of the new user.
     * @return The created user.
     */
    public User createUser(String username, String password, UserType userType, String email) {
        try {
            logger.info("Creating a user with username: " + username + "of user type: " + userType);
            password = passwordEncoder.hashPassword(password);
            User newUser = new User(username, password, userType, email);
            return userRepository.save(newUser);
        } catch (Exception e) {
            logger.error("Error occurred while creating user: " + e.getMessage());
            throw new RuntimeException("Failed to create user", e);
        }
    }

    /**
     * Get all users.
     *
     * @return List of all users.
     */
    public List<User> getAllUsers() {
        try {
            logger.info("Getting all users");
            return userRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all users: " + e.getMessage());
            throw new RuntimeException("Failed to get all users", e);
        }
    }

    /**
     * Get a user by ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return Optional containing the user if found, otherwise empty.
     */
    public Optional<User> getUserById(int userId) {
        try {
            logger.info("Getting user by id: " + userId);
            return userRepository.findById(userId);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving user with id " + userId + ": " + e.getMessage());
            throw new RuntimeException("Failed to get user by id " + userId, e);
        }
    }

    /**
     * Update a user's information.
     *
     * @param userId   The ID of the user to update.
     * @param username The new username.
     * @param password The new password.
     * @param userType The new user type.
     * @param email    The new email.
     * @return The updated user if found, otherwise null.
     */
    public User updateUser(int userId, String username, String password, UserType userType, String email) {
        try {
            logger.info("Trying to update user with id: " + userId);
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                password = passwordEncoder.hashPassword(password);
                User existingUser = optionalUser.get();
                existingUser.setUsername(username);
                existingUser.setPassword(password);
                existingUser.setUserType(userType);
                existingUser.setEmail(email);
                existingUser.setUpdatedAt(LocalDateTime.now());
                logger.info("User with id: " + userId + " is being saved");
                return userRepository.save(existingUser);
            }
            logger.info("Could not find user with id: " + userId);
            return null;
        } catch (Exception e) {
            logger.error("Error occurred while updating user: " + e.getMessage());
            throw new RuntimeException("Failed to update user", e);
        }
    }

    /**
     * Delete a user by ID.
     *
     * @param userId The ID of the user to delete.
     * @return True if the user was deleted, false otherwise.
     */
    public boolean deleteUser(int userId) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                userRepository.deleteById(userId);
                logger.info("User with id: " + userId + " was deleted");
                return true;
            }
            logger.info("Could not find user with id: " + userId);
            return false;
        } catch (Exception e) {
            logger.error("Error occurred while deleting user: " + e.getMessage());
            throw new RuntimeException("Failed to delete user", e);
        }
    }

    /**
     * Verify user's password by username.
     *
     * @param userJSON User credentials containing username and password.
     * @return UserDTO object if the password matches the stored hashed password, null otherwise.
     */
    public UserDTO verifyUserPassword(String userJSON) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(userJSON);
            String username = jsonNode.get("username").asText();
            String password = jsonNode.get("password").asText();

            Optional<User> optionalUser = userRepository.findByUsername(username);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (passwordEncoder.verifyPassword(password, user.getPassword())) {
                    logger.info("User with username: " + username + " was found");
                    UserDTO userDTO = userMapper.toDto(user);
                    return userDTO;
                } else
                    return null;
            }

            logger.info("User with username: " + username + " not found");
            return null;
        } catch (Exception e) {
            logger.error("Error occurred while verifying password for username: " + e.getMessage());
            throw new RuntimeException("Failed to verify password for username", e);
        }
    }


}
