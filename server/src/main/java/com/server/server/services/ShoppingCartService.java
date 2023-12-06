package com.server.server.services;

import com.server.server.models.ShoppingCart;
import com.server.server.models.ShoppingCartItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    private final ShoppingCart shoppingCart = new ShoppingCart();

    public void addItemToCart(ShoppingCartItem item) {
        shoppingCart.addItem(item);
    }

    public void removeItemFromCart(Long productId) {
        shoppingCart.removeItem(productId);
    }

    public List<ShoppingCartItem> getCartItems() {
        return shoppingCart.getItems();
    }

    public double getCartTotal() {
        return shoppingCart.calculateTotal();
    }
}
