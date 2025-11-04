package com.interview.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.api.model.Item;
import com.interview.api.service.ItemService;

// ***********************************************************************************************
// INTERVIEW EXERCISE!
// TODO: Optional - Add/modify tests to verify category filtering and sorting
// ***********************************************************************************************

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
            new Item(1L, "Milk", "1 gallon whole milk", 3.49, 20),
            new Item(2L, "Bread", "Whole wheat loaf", 2.99, 30)
        );
        when(itemService.getAllItems()).thenReturn(items);

        mockMvc.perform(get("/api/items"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].name").value("Milk"))
            .andExpect(jsonPath("$[1].name").value("Bread"));
    }

    @Test
    void testGetItemsByName() throws Exception {
        List<Item> items = Arrays.asList(
            new Item(1L, "Milk", "1 gallon whole milk", 3.49, 20)
        );
        when(itemService.getItemsByName("Milk")).thenReturn(items);

        mockMvc.perform(get("/api/items?name=Milk"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].name").value("Milk"));
    }

    @Test
    void testGetItemById() throws Exception {
        Item item = new Item(1L, "Milk", "1 gallon whole milk", 3.49, 20);
        when(itemService.getItemById(1L)).thenReturn(item);

        mockMvc.perform(get("/api/items/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Milk"))
            .andExpect(jsonPath("$.price").value(3.49));
    }

    @Test
    void testCreateItem() throws Exception {
        Item newItem = new Item(null, "Eggs", "Dozen large eggs", 4.29, 15);
        Item createdItem = new Item(1L, "Eggs", "Dozen large eggs", 4.29, 15);
        when(itemService.createItem(any(Item.class))).thenReturn(createdItem);

        mockMvc.perform(post("/api/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newItem)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Eggs"));
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
        Item updatedItem = new Item(1L, "Updated Milk", "Updated description", 3.99, 25);
        when(itemService.updateItem(eq(1L), any(Item.class))).thenReturn(updatedItem);

        mockMvc.perform(put("/api/items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedItem)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Updated Milk"))
            .andExpect(jsonPath("$.price").value(3.99));
    }

    @Test
    void testDeleteItem() throws Exception {
        mockMvc.perform(delete("/api/items/1"))
            .andExpect(status().isNoContent());
    }
}
