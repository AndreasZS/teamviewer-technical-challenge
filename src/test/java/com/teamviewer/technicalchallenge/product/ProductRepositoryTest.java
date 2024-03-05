package com.teamviewer.technicalchallenge.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindAll() {
        // Arrange
        Product product1 = new Product(1L, "Product 1", "product 1 description", BigDecimal.ONE);
        Product product2 = new Product(2L, "Product 2", "product 2", BigDecimal.ONE);
        this.productRepository.saveAll(List.of(product1, product2));
        // Act
        List<Product> products = this.productRepository.findAll();
        // Assert
        assertEquals(2, products.size());
        assertTrue(products.contains(product1));
        assertTrue(products.contains(product2));

    }

    @Test
    public void testFindById() {
        // Arrange
        Product product1 = new Product(1L, "Product 1", "product 1 description", BigDecimal.ONE);
        this.productRepository.saveAndFlush(product1);
        // Act
        Product product = this.productRepository.findById(1L).orElse(null);
        // Assert
        assertEquals(product1, product);
    }
}