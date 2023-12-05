package com.server.server.controllers;

import com.server.server.dtos.ItemCreationDTO;
import com.server.server.models.Item;
import com.server.server.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody ItemCreationDTO itemDTO) {
        Item createdItem = itemService.createItem(itemDTO.getItemName(), itemDTO.getItemImage(), itemDTO.getSize(),
                itemDTO.getDescription(), itemDTO.getPrice(), itemDTO.getQuantityAvailable());
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable int itemId) {
        Optional<Item> item = itemService.getItemById(itemId);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable int itemId, @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(itemId, item.getItemName(), item.getItemImage(), item.getSize(), item.getDescription(), item.getPrice(), item.getQuantityAvailable());
        return updatedItem != null ?
                new ResponseEntity<>(updatedItem, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable int itemId) {
        boolean deleted = itemService.deleteItem(itemId);
        return deleted ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

