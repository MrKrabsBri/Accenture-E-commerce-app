package com.server.server.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ItemCreationDTO {

    private int itemId;

    private String itemName;

    private String itemImageBase64;

    private String size;

    private String description;

    private BigDecimal price;

    private int quantityAvailable;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ItemCreationDTO(String itemName, String itemImageBase64, String size, String description, BigDecimal price,
            int quantityAvailable) {
        this.itemName = itemName;
        this.itemImageBase64 = itemImageBase64;
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
        return itemImageBase64;
    }

    /**
     * Sets the image of the item.
     *
     * @param itemImage The new image of the item.
     */

    public void setItemImage(String itemImageBase64) {
        this.itemImageBase64 = itemImageBase64;
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
     * Gets the available quantity of the item.
     *
     * @return The available quantity of the item.
     */

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    /**
     * Sets the available quantity of the item.
     *
     * @param quantityAvailable The new available quantity of the item.
     */

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    /**
     * Gets the created at date of the item.
     *
     * @return The created at date of the item.
     */

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the created at date of the item.
     *
     * @param createdAt The new created at date of the item.
     */

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the updated at date of the item.
     *
     * @return The updated at date of the item.
     */

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the updated at date of the item.
     *
     * @param updatedAt The new updated at date of the item.
     */

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
