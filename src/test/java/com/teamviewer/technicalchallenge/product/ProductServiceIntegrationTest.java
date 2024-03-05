package com.teamviewer.technicalchallenge.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductServiceIntegrationTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
//    @Sql("/data.sql") // Optional: Initialize test data using SQL scripts
    void getProducts() {
        // Arrange
        this.productRepository.saveAll(
                List.of(new Product(1L, "Product 1", "product 1 description", BigDecimal.ONE),
                new Product(2L, "Product 2", "product 2", BigDecimal.ONE))
        );
        // Act
        List<Product> products = this.productService.getProducts();
        // Assert
        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @Test
    void getProduct() {
        // Arrange
        Long productId = 1L;
        this.productRepository.save(new Product(1L, "Product 1", "product 1 description", BigDecimal.ONE));
        // Act
        Product product = this.productService.getProduct(productId);
        // Assert
        assertNotNull(product);
        assertEquals(productId, product.getId());
    }

    @Test
    void createProduct() {
        // Arrange
        Product newProduct = new Product(1L, "Product 1", "product 1 description", BigDecimal.ONE);
        // Act
        Product createdProduct = this.productService.createProduct(newProduct);
        // Assert
        assertNotNull(createdProduct);
        assertNotNull(createdProduct.getId());
        assertEquals(newProduct.getName(), createdProduct.getName());
        Product storedProduct = this.productRepository.findById(createdProduct.getId()).orElse(null);
        assertNotNull(storedProduct);
        assertEquals(newProduct.getName(), storedProduct.getName());
    }

    @Test
    void updateProduct() {
        // Arrange
        Long productId = 1L;
        Product existingProduct = new Product(productId, "Existing Product", "existing description", BigDecimal.ONE);
        this.productRepository.save(existingProduct);
        Product updatedProduct = new Product(productId, "Updated Name", "Updated description",
                BigDecimal.ONE);
        // Act
        Product product = this.productService.updateProduct(productId, updatedProduct);
        // Assert
        assertNotNull(product);
        assertEquals(updatedProduct.getName(), product.getName());
        Product storedProduct = this.productRepository.findById(updatedProduct.getId()).orElse(null);
        assertNotNull(storedProduct);
        assertEquals(updatedProduct.getName(), storedProduct.getName());
    }

    @Test
    void deleteProduct() {
        // Arrange
        Long productId = 1L;
        this.productRepository.save(new Product(1L, "Product 1", "product 1 description", BigDecimal.ONE));
        // Act
        this.productService.deleteProduct(productId);
        // Assert
        Product deletedProduct = this.productRepository.findById(productId).orElse(null);
        assertNull(deletedProduct);
    }
}