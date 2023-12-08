package com.server.server.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    @Column(nullable = false, length = 100)
    private String itemName;

    @Column(nullable = false, length = 500)
    private String itemImage;

    @Column(nullable = false, length = 40)
    private String size;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantityAvailable;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public Item() {
    }

    public Item(String itemName, String itemImage, String size, String description, BigDecimal price,
            int quantityAvailable) {
        this.itemName = itemName;
        this.itemImage = itemImage;
        this.size = size;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Gets the Item ID.
     *
     * @return The Item ID.
     */

    public int getItemId() {
        return itemId;
    }

    /**
     * Sets the ID of the item.
     *
     * @param id The new ID of the item.
     */

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets the name of the item.
     *
     * @return The name of the item.
     */

    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the name of the item.
     *
     * @param itemName The new name of the item.
     */

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets the image of the item.
     *
     * @return The image of the item.
     */

    public String getItemImage() {
        return itemImage;
    }

    /**
     * Sets the image of the item.
     *
     * @param itemImage The new image of the item.
     */

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    /**
     * Gets the size of the item.
     *
     * @return The size of the item.
     */

    public String getSize() {
        return size;
    }

    /**
     * Sets the size of the item.
     *
     * @param size The new size of the item.
     */

    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Gets the description of the item.
     *
     * @return The description of the item.
     */

    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the item.
     *
     * @param description The new description of the item.
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the price of the item.
     *
     * @return The price of the item.
     */

    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price of the item.
     *
     * @param price The new price of the item.
     */

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the quantity available of the item.
     *
     * @return The quantity available of the item.
     */

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    /**
     * Sets the quantity available of the item.
     *
     * @param quantityAvailable The new quantity available of the item.
     */

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    /**
     * Gets the date and time the item was created.
     *
     * @return The date and time the item was created.
     */

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the date and time the item was created.
     *
     * @param createdAt The new date and time the item was created.
     */

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the date and time the item was last updated.
     *
     * @return The date and time the item was last updated.
     */

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the date and time the item was last updated.
     *
     * @param updatedAt The new date and time the item was last updated.
     */

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}