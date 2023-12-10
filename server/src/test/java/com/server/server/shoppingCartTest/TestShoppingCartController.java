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
    void creates_a_ShoppingCartItem() throws Exception {

        ShoppingCartItem item = new ShoppingCartItem(2, 13, 1L, 2);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());

    }
}
