package com.server.server.controllers;

import com.server.server.models.ShoppingCartItem;
import com.server.server.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing shopping cart operations.
 * Exposes endpoints for adding, removing, and retrieving items in the shopping
 * cart.
 */
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Endpoint to add an item to the shopping cart.
     *
     * @param item The item to be added to the cart.
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItemToCart(@RequestBody ShoppingCartItem item) {
        shoppingCartService.addItemToCart(item);
    }

    /**
     * Endpoint to remove an item from the shopping cart.
     *
     * @param userId    The unique identifier of the user whose item will be
     *                  removed.
     * @param productId The unique identifier of the product to be removed.
     */
    @DeleteMapping("/remove/{userId}/{productId}")
    public void removeItemFromCart(@PathVariable long userId, @PathVariable Long productId) {
        shoppingCartService.removeItemFromCart(userId, productId);
    }

    /**
     * Endpoint to retrieve all items in the shopping cart.
     *
     * @return List of ShoppingCartItem containing all items in the cart.
     */
    @GetMapping("/items")
    public List<ShoppingCartItem> getCartItems() {
        return shoppingCartService.getCartItems();
    }

    /**
     * Endpoint to retrieve cart items by userId.
     *
     * @param userId The unique identifier of the user to fetch cart items.
     * @return List of ShoppingCartItem containing items in the cart for the
     *         specified user.
     */
    @GetMapping("/items/{userId}")
    public List<ShoppingCartItem> getCartItemsByUserId(@PathVariable long userId) {
        return shoppingCartService.getCartItemsByUserId(userId);
    }
}
