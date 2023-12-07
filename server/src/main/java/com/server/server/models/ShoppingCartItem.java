package com.server.server.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Shopping_Cart_Item")
public class ShoppingCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long cartItemId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "quantity")
    private int quantity;

    public ShoppingCartItem() {

    }

    public ShoppingCartItem(long cartItemId, long userId, Long itemId, int quantity) {
        this.cartItemId = cartItemId;
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
    }


    /**
     * Gets the Cart item ID.
     *
     * @return The Cart item ID.
     */
    public long getCartItemId() {
        return cartItemId;
    }

    /**
     * Gets the User ID.
     *
     * @return The User ID.
     */

    public long getUserId() {
        return userId;
    }

    /**
     * Gets the Item ID.
     *
     * @return The Item ID.
     */

    public Long getItemId() {
        return itemId;
    }

    /**
     * Gets the quantity of the item.
     *
     * @return The quantity of the item.
     */

    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the Cart item ID.
     *
     * @param cartItemId The new Cart item ID.
     */

    public void setCartItemId(long cartItemId) {
        this.cartItemId = cartItemId;
    }

    /**
     * Sets the User ID.
     *
     * @param userId The new User ID.
     */

    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Sets the Item ID.
     *
     * @param itemId The new Item ID.
     */

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * Sets the quantity of the item.
     *
     * @param quantity The new quantity of the item.
     */

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
