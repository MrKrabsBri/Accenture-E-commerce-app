package com.server.server.controllers;

import com.server.server.models.ShoppingCartItem;
import com.server.server.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public void addItemToCart(@RequestBody ShoppingCartItem item) {
        shoppingCartService.addItemToCart(item);
    }

    @DeleteMapping("/remove/{productId}")
    public void removeItemFromCart(@PathVariable Long productId) {
        shoppingCartService.removeItemFromCart(productId);
    }

    @GetMapping("/items")
    public List<ShoppingCartItem> getCartItems() {
        return shoppingCartService.getCartItems();
    }

    @GetMapping("/total")
    public double getCartTotal() {
        return shoppingCartService.getCartTotal();
    }
}