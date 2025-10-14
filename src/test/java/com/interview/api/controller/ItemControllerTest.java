package com.interview.api.controller;

import com.interview.api.model.Item;
import com.interview.api.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ItemService itemService;

    @Test
    void testGetAllItems() throws Exception {
        List<Item> items = Arrays.asList(
                new Item(1L, "Laptop", "High-performance laptop", 1299.99, 10),
                new Item(2L, "Mouse", "Wireless mouse", 29.99, 50)
        );

        when(itemService.getAllItems()).thenReturn(items);

        mockMvc.perform(get("/api/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Laptop"))
                .andExpect(jsonPath("$[1].name").value("Mouse"));
    }

    @Test
    void testGetItemsByName() throws Exception {
        List<Item> items = Arrays.asList(
                new Item(1L, "Laptop", "High-performance laptop", 1299.99, 10)
        );

        when(itemService.getItemsByName("Laptop")).thenReturn(items);

        mockMvc.perform(get("/api/items?name=Laptop"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Laptop"));
    }

    @Test
    void testGetItemById() throws Exception {
        Item item = new Item(1L, "Laptop", "High-performance laptop", 1299.99, 10);

        when(itemService.getItemById(1L)).thenReturn(item);

        mockMvc.perform(get("/api/items/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"))
                .andExpect(jsonPath("$.price").value(1299.99));
    }

    @Test
    void testCreateItem() throws Exception {
        Item newItem = new Item(null, "Keyboard", "Mechanical keyboard", 89.99, 25);
        Item createdItem = new Item(1L, "Keyboard", "Mechanical keyboard", 89.99, 25);

        when(itemService.createItem(any(Item.class))).thenReturn(createdItem);

        mockMvc.perform(post("/api/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newItem)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Keyboard"));
    }

    @Test
    void testCreateItemWithValidationError() throws Exception {
        Item invalidItem = new Item(null, "", "Description", -10.0, -5);

        mockMvc.perform(post("/api/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidItem)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors").exists());
    }

    @Test
    void testUpdateItem() throws Exception {
        Item updatedItem = new Item(1L, "Updated Laptop", "Updated description", 1499.99, 15);

        when(itemService.updateItem(eq(1L), any(Item.class))).thenReturn(updatedItem);

        mockMvc.perform(put("/api/items/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Laptop"))
                .andExpect(jsonPath("$.price").value(1499.99));
    }

    @Test
    void testDeleteItem() throws Exception {
        mockMvc.perform(delete("/api/items/1"))
                .andExpect(status().isNoContent());
    }
}
