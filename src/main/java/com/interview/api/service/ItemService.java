package com.interview.api.service;

import com.interview.api.exception.ItemNotFoundException;
import com.interview.api.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ConcurrentHashMap<Long, Item> items = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ItemService() {
        // Initialize with some sample items
        Item item1 = new Item(idGenerator.getAndIncrement(), "Laptop", "High-performance laptop", 1299.99, 10);
        Item item2 = new Item(idGenerator.getAndIncrement(), "Mouse", "Wireless mouse", 29.99, 50);
        Item item3 = new Item(idGenerator.getAndIncrement(), "Keyboard", "Mechanical keyboard", 89.99, 25);
        
        items.put(item1.getId(), item1);
        items.put(item2.getId(), item2);
        items.put(item3.getId(), item3);
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
