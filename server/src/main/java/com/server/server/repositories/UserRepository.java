package com.server.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.server.server.models.User;

/**
 * Repository interface for User entity providing CRUD operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}