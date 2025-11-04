package com.interview.api.service;

import com.interview.api.exception.ItemNotFoundException;
import com.interview.api.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// ***********************************************************************************************
// INTERVIEW EXERCISE!
// TODO: Update sample items to include category
// TODO: Add a method to filter items by category
// TODO: Add a method to return items sorted by name, price, or quantity
//       Use a parameter to specify the sort field and direction
// ***********************************************************************************************

@Service
public class ItemService {

    private final ConcurrentHashMap<Long, Item> items = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ItemService() {
        // Initialize with grocery sample items
        addSampleItem("Milk", "1 gallon whole milk", 3.49, 20);
        addSampleItem("Bread", "Whole wheat loaf", 2.99, 30);
        addSampleItem("Eggs", "Dozen large eggs", 4.29, 15);
        addSampleItem("Butter", "Salted butter 16oz", 5.49, 10);
        addSampleItem("Cheese", "Cheddar cheese block 8oz", 4.99, 12);
        addSampleItem("Apples", "Fresh red apples (per lb)", 1.99, 50);
        addSampleItem("Bananas", "Yellow bananas (per lb)", 0.79, 40);
        addSampleItem("Chicken Breast", "Boneless skinless chicken breast (per lb)", 6.99, 25);
        addSampleItem("Ground Beef", "Lean ground beef (per lb)", 7.49, 18);
        addSampleItem("Rice", "Long grain white rice 5lb bag", 8.99, 8);
        addSampleItem("Pasta", "Spaghetti pasta 16oz", 1.49, 35);
        addSampleItem("Tomatoes", "Fresh Roma tomatoes (per lb)", 2.49, 22);
    }

    private void addSampleItem(String name, String description, double price, int quantity) {
        Item item = new Item(idGenerator.getAndIncrement(), name, description, price, quantity);
        items.put(item.getId(), item);
    }

    public List<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }

    public List<Item> getItemsByName(String name) {
        return items.values().stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Item> getItemsByPriceRange(Double minPrice, Double maxPrice) {
        return items.values().stream()
                .filter(item -> {
                    boolean meetsMin = minPrice == null || item.getPrice() >= minPrice;
                    boolean meetsMax = maxPrice == null || item.getPrice() <= maxPrice;
                    return meetsMin && meetsMax;
                })
                .collect(Collectors.toList());
    }

    public Item getItemById(Long id) {
        Item item = items.get(id);
        if (item == null) {
            throw new ItemNotFoundException(id);
        }
        return item;
    }

    public Item createItem(Item item) {
        Long id = idGenerator.getAndIncrement();
        item.setId(id);
        items.put(id, item);
        return item;
    }

    public Item updateItem(Long id, Item updatedItem) {
        if (!items.containsKey(id)) {
            throw new ItemNotFoundException(id);
        }
        updatedItem.setId(id);
        items.put(id, updatedItem);
        return updatedItem;
    }

    public void deleteItem(Long id) {
        if (!items.containsKey(id)) {
            throw new ItemNotFoundException(id);
        }
        items.remove(id);
    }
}
