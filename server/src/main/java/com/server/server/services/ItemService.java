package com.server.server.services;

import com.server.server.models.Item;
import com.server.server.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private static final Logger logger = LogManager.getLogger();
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(String itemName, String itemImageBase64, String size, String description, BigDecimal price, int quantityAvailable) {
        try {
            Item newItem = new Item(itemName, itemImageBase64, size, description, price, quantityAvailable);
            return itemRepository.save(newItem);
        } catch (Exception e) {
            logger.error("Error occurred while creating item: " + e.getMessage());
            throw new RuntimeException("Failed to add item", e);
        }
    }


    public List<Item> getAllItems() {
        try {
            logger.info("Getting all items");
            return itemRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving all items: " + e.getMessage());
            throw new RuntimeException("Failed to get all items", e);
        }
    }

    public Optional<Item> getItemById(int itemId) {
        try {
            logger.info("Getting item by id: " + itemId);
            return itemRepository.findById(itemId);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving item with id " + itemId + ": " + e.getMessage());
            throw new RuntimeException("Failed to get item by id " + itemId, e);
        }
    }

    public Item updateItem(int itemId, String itemName, String itemImage, String size, String description, BigDecimal price, int quantityAvailable) {
        try {
            logger.info("Trying to update item with id: " + itemId);
            Optional<Item> optionalItem = itemRepository.findById(itemId);
            if (optionalItem.isPresent()) {
                Item existingItem = optionalItem.get();
                existingItem.setItemName(itemName);
                existingItem.setItemImage(itemImage);
                existingItem.setSize(size);
                existingItem.setDescription(description);
                existingItem.setPrice(price);
                existingItem.setQuantityAvailable(quantityAvailable);
                existingItem.setUpdatedAt(LocalDateTime.now());
                logger.info("Item with id: " + itemId + " is being saved");
                return itemRepository.save(existingItem);
            }
            logger.info("Could not find item with id: " + itemId);
            return null;
        } catch (Exception e) {
            logger.error("Error occurred while updating item: " + e.getMessage());
            throw new RuntimeException("Failed to update item", e);
        }
    }

    public boolean deleteItem(int itemId) {
        try {
            Optional<Item> optionalItem = itemRepository.findById(itemId);
            if (optionalItem.isPresent()) {
                itemRepository.deleteById(itemId);
                logger.info("Item with id: " + itemId + " was deleted");
                return true;
            }
            logger.info("Could not find item with id: " + itemId);
            return false;
        } catch (Exception e) {
            logger.error("Error occurred while deleting item: " + e.getMessage());
            throw new RuntimeException("Failed to delete item", e);
        }
    }
}
