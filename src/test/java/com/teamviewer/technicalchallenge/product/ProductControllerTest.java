package com.teamviewer.technicalchallenge.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.xml.transform.Result;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProducts() throws Exception {
        // Arrange
        // Act
        ResultActions result = mockMvc.perform(get("/api/products"));

    }

    @Disabled
    @Test
    void getProduct() throws Exception {
        // Arrange
        Long productId = 1L;
        // Act
        ResultActions result = mockMvc.perform(get("/api/products/{id}", productId));
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.name").value("Product 1"))
                .andExpect(jsonPath("$.description").value("product 1 description"))
                .andExpect(jsonPath("$.price").value(BigDecimal.ONE));
    }

    @Test
    void getProductNotFound() throws Exception {
        // Arrange
        Long productId = 1L;
        // Act
        ResultActions result = mockMvc.perform(get("/api/products/{id}", productId));
        // Assert
        result.andExpect(status().isNotFound());
    }

    @Test
    void createProduct() throws Exception {
        // Arrange
        String productJson = this.objectMapper.writeValueAsString(new Product(1L, "Product 1", "product 1 description",
                BigDecimal.ONE));
        // Act
        ResultActions result = mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson));
        // Assert
        result.andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/products/1"));
    }

    @Disabled
    @Test
    void updateProduct() throws Exception {
        // Arrange
        Long productId = 1L;
        String productJson = this.objectMapper.writeValueAsString(new Product(productId, "Updated Name",
                "Updated description", BigDecimal.TEN));
        // Act
        ResultActions result = mockMvc.perform(put("/api/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson));
        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(productId))
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.description").value("Updated description"))
                .andExpect(jsonPath("$.price").value(BigDecimal.TEN));
    }

    @Test
    void deleteMapping() throws Exception {
        // Arrange
        Long productId = 1L;
        // Act
        ResultActions result = mockMvc.perform(delete("/api/products/{id}", productId));
        // Assert
        result.andExpect(status().isOk());
    }

}