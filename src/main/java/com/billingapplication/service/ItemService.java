package com.billingapplication.service;

import com.billingapplication.model.ExpenseItem;
import com.billingapplication.repo.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ExpenseItem> findByItemName(String itemName) {
        return itemRepository.findByItemname(itemName);
    }

    public ExpenseItem saveItem(ExpenseItem item) {
        return itemRepository.save(item);
    }

    public ExpenseItem updateItem(Long id, ExpenseItem itemDetails) {
        ExpenseItem item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("ExpenseItem not found"));
        item.setItemname(itemDetails.getItemname());
        item.setItemprice(itemDetails.getItemprice());
        item.setDescription(itemDetails.getDescription());
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public List<ExpenseItem> findAllItems() {
        return itemRepository.findAll();
    }
}
