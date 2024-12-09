package com.billingapplication.controller;

import com.billingapplication.model.ExpenseItem;
import com.billingapplication.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemName}")
    public ResponseEntity<List<ExpenseItem>> getItemsByName(@PathVariable String itemName) {
        List<ExpenseItem> items = itemService.findByItemName(itemName);
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<ExpenseItem> createItem(@RequestBody ExpenseItem item) {
        ExpenseItem savedItem = itemService.saveItem(item);
        return ResponseEntity.ok(savedItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseItem> updateItem(@PathVariable Long id, @RequestBody ExpenseItem itemDetails) {
        ExpenseItem updatedItem = itemService.updateItem(id, itemDetails);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ExpenseItem>> getAllItems() {
        List<ExpenseItem> items = itemService.findAllItems();
        return ResponseEntity.ok(items);
    }
}
