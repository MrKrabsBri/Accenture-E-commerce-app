package com.server.server.services;

import com.server.server.models.ShoppingCartItem;
import com.server.server.repositories.ShoppingCartRepository;

import jakarta.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository cartItemRepository;
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    public ShoppingCartService(ShoppingCartRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    /**
     * Checks if an item is already in the shopping cart.
     *
     * @param item The item to be checked.
     * @return true if the item exists in the shopping cart, false otherwise.
     */
    public boolean checkIfExists(ShoppingCartItem item) {
        try {
            List<ShoppingCartItem> existingItems = cartItemRepository.findByUserIdAndItemId(item.getUserId(),
                    item.getItemId());
            logger.info("Checking for item existence: " + item.getItemId());
            return !existingItems.isEmpty();
        } catch (Exception e) {
            logger.error("Error occurred while checking for item existence: " + e.getMessage());
            throw new RuntimeException("Failed to check for item existence", e);
        }
    }

    /**
     * Adds an item to the shopping cart.
     *
     * @param item The item to be added.
     */
    public void addItemToCart(ShoppingCartItem item) {
        try {
            logger.info("Adding item to cart: " + item.getItemId());
            if (!checkIfExists(item))
                cartItemRepository.save(item);
        } catch (Exception e) {
            logger.error("Error occurred while adding item to cart: " + e.getMessage());
            throw new RuntimeException("Failed to add item to cart", e);
        }
    }

    /**
     * Removes an item from the shopping cart for a specific user.
     *
     * @param userId The ID of the user.
     * @param itemId The ID of the item to be removed.
     */
    @Transactional
    public void removeItemFromCart(Long userId, Long itemId) {
        try {
            logger.info("Removing item from cart: " + itemId + " for user: " + userId);
            cartItemRepository.deleteByUserIdAndItemId(userId, itemId);
        } catch (Exception e) {
            logger.error("Error occurred while removing item from cart: " + e.getMessage());
            throw new RuntimeException("Failed to remove item from cart", e);
        }
    }

    /**
     * Retrieves all items in the shopping cart.
     *
     * @return List of ShoppingCartItem representing all items in the cart.
     */
    public List<ShoppingCartItem> getCartItems() {
        try {
            logger.info("Fetching all cart items");
            return cartItemRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while fetching all cart items: " + e.getMessage());
            throw new RuntimeException("Failed to fetch cart items", e);
        }
    }

    /**
     * Fetches cart items for a specific user identified by userId.
     *
     * @param userId The unique identifier of the user.
     * @return List of ShoppingCartItem containing items in the cart for the
     *         specified user.
     */
    public List<ShoppingCartItem> getCartItemsByUserId(long userId) {
        try {
            logger.info("Fetching cart items by user ID: " + userId);
            return cartItemRepository.findByUserId(userId);
        } catch (Exception e) {
            logger.error("Error occurred while fetching cart items by user ID: " + e.getMessage());
            throw new RuntimeException("Failed to fetch cart items by user ID", e);
        }
    }
}
