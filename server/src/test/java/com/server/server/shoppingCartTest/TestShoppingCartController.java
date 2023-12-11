package com.server.server.shoppingCartTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.server.controllers.ShoppingCartController;
import com.server.server.models.ShoppingCartItem;
import com.server.server.services.ShoppingCartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = ShoppingCartController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TestShoppingCartController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_adds_an_item_to_shopping_cart() throws Exception {

        ShoppingCartItem item = new ShoppingCartItem(2, 13, 1L, 2);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    void test_get_All_ShoppingCartItems() throws Exception {
        // Mock the behavior of the service to return a list of items
        List<ShoppingCartItem> itemList = Arrays.asList(
                new ShoppingCartItem(1L, 13, 1L, 3),
                new ShoppingCartItem(2L, 14, 2L, 1)
        );
        when(shoppingCartService.getCartItems()).thenReturn(itemList);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/cart/items"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(itemList)));
    }

    @Test
    void gets_ShoppingCartItem_By_ID() throws Exception {
        int userId= 14;
        List<ShoppingCartItem> itemList = Collections.singletonList(new ShoppingCartItem(2L, userId, 1L, 3));
        when(shoppingCartService.getCartItemsByUserId(userId)).thenReturn(itemList);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/cart/items/{userId}",userId));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(itemList)));
    }

}
