package com.teamviewer.technicalchallenge.order;

import com.fasterxml.jackson.databind.ObjectMapper;
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
class OrderControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderRepository orderRepository;


    Order createTestOrder(Long id) {
        return new Order(id, null, null, Status.IN_PROGRESS, null);
    }

    @Test
    void getOrders() throws Exception {
        // Arrange
        this.orderRepository.saveAll(
                List.of(createTestOrder(1L),
                        createTestOrder(2L))
        );
        // Act
        ResultActions result = mockMvc.perform(get("/api/orders"));
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("length($)").value(2));

    }

    @Test
    void getOrder() throws Exception {
        // Arrange
        Long orderId = 1L;
        this.orderRepository.save(createTestOrder(orderId));
        // Act
        ResultActions result = mockMvc.perform(get("/api/orders/{id}", orderId));
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(orderId))
                .andExpect(jsonPath("$.status").value(Status.IN_PROGRESS.toString()));
    }

    @Test
    void getOrderNotFound() throws Exception {
        // Arrange
        Long orderId = 1L;
        // Act
        ResultActions result = mockMvc.perform(get("/api/orders/{id}", orderId));
        // Assert
        result.andExpect(status().isNotFound());
    }

    @Test
    void createOrder() throws Exception {
        // Arrange
        String orderJson = this.objectMapper.writeValueAsString(createTestOrder(1L));
        // Act
        ResultActions result = mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderJson));
        // Assert
        result.andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/orders/1"));
    }

    @Test
    void updateOrder() throws Exception {
        // Arrange
        Long orderId = 1L;
        this.orderRepository.save(createTestOrder(orderId));
        String orderJson = this.objectMapper.writeValueAsString(new Order(orderId, null, null,
                Status.COMPLETED, List.of()));
        // Act
        ResultActions result = mockMvc.perform(put("/api/orders/{id}", orderId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderJson));
        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(orderId))
                .andExpect(jsonPath("$.status").value(Status.COMPLETED.toString()));
    }

    @Test
    void deleteMapping() throws Exception {
        // Arrange
        Long orderId = 1L;
        this.orderRepository.save(createTestOrder(orderId));
        // Act
        ResultActions result = mockMvc.perform(delete("/api/orders/{id}", orderId));
        // Assert
        result.andExpect(status().isOk());
    }

}