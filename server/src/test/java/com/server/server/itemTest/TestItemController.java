package com.server.server.itemTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.server.controllers.ItemController;
import com.server.server.models.Item;
import com.server.server.services.ItemService;
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
import java.math.BigDecimal;


@WebMvcTest(ItemController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TestItemController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void test_adds_an_item_to_shop_listing() throws Exception {

        Item newItem = new Item("Sneakers", "Description", "45",
                "Oversized", BigDecimal.valueOf(129.99), 45);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newItem)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }


}
