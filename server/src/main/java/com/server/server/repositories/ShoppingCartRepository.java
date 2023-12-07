package com.server.server.repositories;

import com.server.server.models.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ShoppingCartItem entity providing CRD operations.
 */
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartItem, Long> {
    void deleteByUserIdAndItemId(Long userId, Long itemId);
}