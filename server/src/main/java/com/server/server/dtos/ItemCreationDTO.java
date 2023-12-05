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

    public ItemCreationDTO(String itemName, String itemImageBase64, String size, String description, BigDecimal price, int quantityAvailable) {
        this.itemName = itemName;
        this.itemImageBase64 = itemImageBase64;
        this.size = size;
        this.description = description;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemImage() {
        return itemImageBase64;
    }

    public void setItemImage(String itemImageBase64) {
        this.itemImageBase64 = itemImageBase64;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
