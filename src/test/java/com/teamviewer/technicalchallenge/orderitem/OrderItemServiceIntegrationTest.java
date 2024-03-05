package com.teamviewer.technicalchallenge.orderitem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderItemServiceIntegrationTest {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemService orderItemService;


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
//    @Sql("/data.sql") // Optional: Initialize test data using SQL scripts
    void getOrderItems() {
        // Arrange
        this.orderItemRepository.saveAll(
                List.of(createTestOrderItem(1L),
                createTestOrderItem(2L))
        );
        // Act
        List<OrderItem> orderItems = this.orderItemService.getOrderItems();
        // Assert
        assertNotNull(orderItems);
        assertEquals(2, orderItems.size());
    }

    @Test
    void getOrderItem() {
        // Arrange
        Long orderItemId = 1L;
        this.orderItemRepository.save(createTestOrderItem(orderItemId));
        // Act
        OrderItem orderItem = this.orderItemService.getOrderItem(orderItemId);
        // Assert
        assertNotNull(orderItem);
        assertEquals(orderItemId, orderItem.getId());
    }

    @Test
    void createOrderItem() {
        // Arrange
        OrderItem newOrderItem = createTestOrderItem(1L);
        // Act
        OrderItem createdOrderItem = this.orderItemService.createOrderItem(newOrderItem);
        // Assert
        assertNotNull(createdOrderItem);
        assertNotNull(createdOrderItem.getId());
        assertEquals(newOrderItem.getId(), createdOrderItem.getId());
        assertEquals(newOrderItem.getQuantity(), createdOrderItem.getQuantity());
        OrderItem storedOrderItem = this.orderItemRepository.findById(createdOrderItem.getId()).orElse(null);
        assertNotNull(storedOrderItem);
        assertEquals(newOrderItem.getId(), storedOrderItem.getId());
        assertEquals(newOrderItem.getQuantity(), storedOrderItem.getQuantity());
    }

    @Test
    void updateOrderItem() {
        // Arrange
        Long orderItemId = 1L;
        OrderItem existingOrderItem = createTestOrderItem(orderItemId);
        this.orderItemRepository.save(existingOrderItem);
        OrderItem updatedOrderItem = new OrderItem(orderItemId, 1, null, null);
        // Act
        OrderItem orderItem = this.orderItemService.updateOrderItem(orderItemId, updatedOrderItem);
        // Assert
        assertNotNull(orderItem);
        assertEquals(updatedOrderItem.getId(), orderItem.getId());
        assertEquals(updatedOrderItem.getQuantity(), orderItem.getQuantity());
        OrderItem storedOrderItem = this.orderItemRepository.findById(updatedOrderItem.getId()).orElse(null);
        assertNotNull(storedOrderItem);
        assertEquals(updatedOrderItem.getId(), storedOrderItem.getId());
        assertEquals(updatedOrderItem.getQuantity(), storedOrderItem.getQuantity());
    }

    @Test
    void deleteOrderItem() {
        // Arrange
        Long orderItemId = 1L;
        this.orderItemRepository.save(createTestOrderItem(orderItemId));
        // Act
        this.orderItemService.deleteOrderItem(orderItemId);
        // Assert
        OrderItem deletedOrderItem = this.orderItemRepository.findById(orderItemId).orElse(null);
        assertNull(deletedOrderItem);
    }
}