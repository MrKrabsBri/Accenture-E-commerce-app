package com.server.server.services;

import com.server.server.models.ShoppingCartItem;
import com.server.server.repositories.ShoppingCartRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository cartItemRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    /**
     * Adds an item to the shopping cart.
     *
     * @param item The item to be added.
     */
    public void addItemToCart(ShoppingCartItem item) {
        cartItemRepository.save(item);
    }

    /**
     * Removes an item from the shopping cart for a specific user.
     *
     * @param userId The ID of the user.
     * @param itemId The ID of the item to be removed.
     */
    @Transactional
    public void removeItemFromCart(Long userId, Long itemId) {
        cartItemRepository.deleteByUserIdAndItemId(userId, itemId);
    }

    /**
     * Retrieves all items in the shopping cart.
     *
     * @return List of ShoppingCartItem representing all items in the cart.
     */
    public List<ShoppingCartItem> getCartItems() {
        return cartItemRepository.findAll();
    }
}
