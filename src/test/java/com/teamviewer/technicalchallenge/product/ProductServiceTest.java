package com.teamviewer.technicalchallenge.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProducts() {
        // Arrange
        when(this.productRepository.findAll())
                .thenReturn(List.of(new Product(1L, "Product 1", "product 1 description", BigDecimal.ONE),
                        new Product(2L, "Product 2", "product 2", BigDecimal.ONE)));
        // Act
        List<Product> products = this.productService.getProducts();
        // Assert
        verify(this.productRepository, times(1)).findAll();
    }

    @Test
    void getProduct() {
        // Arrange
        Long productId = 1L;
        when(this.productRepository.findById(productId))
                .thenReturn(Optional.of(new Product(productId, "Product 1", "product 1 description", BigDecimal.ONE)));
        // Act
        Product product = this.productService.getProduct(productId);
        // Assert
        verify(this.productRepository, times(1)).findById(productId);
    }

    @Test
    void createProduct() {
        // Arrange
        Product newProduct = new Product(null, "Product 1", "product 1 description", BigDecimal.ONE);
        when(this.productRepository.save(newProduct))
                .thenReturn(new Product(1L, "Product 1", "product 1 description", BigDecimal.ONE));
        // Act
        Product createdProduct = this.productService.createProduct(newProduct);
        // Assert
        verify(this.productRepository, times(1)).save(newProduct);
    }

    @Test
    void updateProduct() {
        // Arrange
        Long productId = 1L;
        Product existingProduct = new Product(productId, "Existing Product", "existing description", BigDecimal.ONE);
        when(this.productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        Product updatedProduct = new Product(productId, "Updated Name", "updated description", BigDecimal.TEN);
        when(this.productRepository.save(updatedProduct)).thenReturn(updatedProduct);
        // Act
        Product product = this.productService.updateProduct(productId, updatedProduct);
        // Assert
        verify(this.productRepository, times(1)).save(updatedProduct);
    }

    @Test
    void deleteProduct() {
        // Arrange
        Long productId = 1L;
        when(this.productRepository.existsById(productId)).thenReturn(true);
        // Act
        this.productService.deleteProduct(productId);
        // Assert
        verify(this.productRepository, times(1)).deleteById(productId);
    }
}