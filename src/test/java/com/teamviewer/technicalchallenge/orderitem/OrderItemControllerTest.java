package com.teamviewer.technicalchallenge.orderitem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderItemControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderItemRepository orderItemRepository;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }
    
    OrderItem createTestOrderItem(Long id) {
        return new OrderItem(id, 0, null, null);
    }

    @Test
    void getOrderItems() throws Exception {
        // Arrange
        this.orderItemRepository.saveAll(
                List.of(createTestOrderItem(1L),
                        createTestOrderItem(2L))
        );
        // Act
        ResultActions result = mockMvc.perform(get("/api/order-items"));
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("length($)").value(2));

    }

    @Test
    void getOrderItem() throws Exception {
        // Arrange
        Long orderItemId = 1L;
        this.orderItemRepository.save(createTestOrderItem(orderItemId));
        // Act
        ResultActions result = mockMvc.perform(get("/api/order-items/{id}", orderItemId));
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(orderItemId))
                .andExpect(jsonPath("$.quantity").value(0));
    }

    @Test
    void getOrderItemNotFound() throws Exception {
        // Arrange
        Long orderItemId = 1L;
        // Act
        ResultActions result = mockMvc.perform(get("/api/order-items/{id}", orderItemId));
        // Assert
        result.andExpect(status().isNotFound());
    }

    @Test
    void createOrderItem() throws Exception {
        // Arrange
        String orderItemJson = this.objectMapper.writeValueAsString(createTestOrderItem(1L));
        // Act
        ResultActions result = mockMvc.perform(post("/api/order-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderItemJson));
        // Assert
        result.andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/order-items/1"));
    }

    @Test
    void updateOrderItem() throws Exception {
        // Arrange
        Long orderItemId = 1L;
        this.orderItemRepository.save(createTestOrderItem(orderItemId));
        String orderItemJson = this.objectMapper.writeValueAsString(new OrderItem(orderItemId, 1, null,
                null));
        // Act
        ResultActions result = mockMvc.perform(put("/api/order-items/{id}", orderItemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderItemJson));
        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderItemId))
                .andExpect(jsonPath("$.quantity").value(1));
    }

    @Test
    void deleteMapping() throws Exception {
        // Arrange
        Long orderItemId = 1L;
        this.orderItemRepository.save(createTestOrderItem(orderItemId));
        // Act
        ResultActions result = mockMvc.perform(delete("/api/order-items/{id}", orderItemId));
        // Assert
        result.andExpect(status().isOk());
    }

}