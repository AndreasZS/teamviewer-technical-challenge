package com.teamviewer.technicalchallenge.order;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderServiceIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    Order createTestOrder(Long id) {
        return new Order(id, null, null, Status.IN_PROGRESS, null);
    }

    @Test
//    @Sql("/data.sql") // Optional: Initialize test data using SQL scripts
    void getOrders() {
        // Arrange
        this.orderRepository.saveAll(
                List.of(createTestOrder(1L),
                        createTestOrder(2L))
        );
        // Act
        List<Order> orders = this.orderService.getOrders();
        // Assert
        assertNotNull(orders);
        assertEquals(2, orders.size());
    }

    @Test
    void getOrder() {
        // Arrange
        Long orderId = 1L;
        this.orderRepository.save(createTestOrder(orderId));
        // Act
        Order order = this.orderService.getOrder(orderId);
        // Assert
        assertNotNull(order);
        assertEquals(orderId, order.getId());
    }

    @Test
    void createOrder() {
        // Arrange
        Order newOrder = createTestOrder(1L);
        // Act
        Order createdOrder = this.orderService.createOrder(newOrder);
        // Assert
        assertNotNull(createdOrder);
        assertNotNull(createdOrder.getId());
        assertEquals(newOrder.getStatus(), createdOrder.getStatus());
        Order storedOrder = this.orderRepository.findById(createdOrder.getId()).orElse(null);
        assertNotNull(storedOrder);
        assertEquals(newOrder.getStatus(), storedOrder.getStatus());
    }

    @Test
    void updateOrder() {
        // Arrange
        Long orderId = 1L;
        Order existingOrder = createTestOrder(orderId);
        this.orderRepository.save(existingOrder);
        Order updatedOrder = new Order(orderId, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()),
                Status.COMPLETED, List.of());
        // Act
        Order order = this.orderService.updateOrder(orderId, updatedOrder);
        // Assert
        assertNotNull(order);
        assertEquals(updatedOrder.getId(), order.getId());
        assertEquals(updatedOrder.getStatus(), order.getStatus());
        Order storedOrder = this.orderRepository.findById(updatedOrder.getId()).orElse(null);
        assertNotNull(storedOrder);
        assertEquals(updatedOrder.getStatus(), storedOrder.getStatus());
    }

    @Test
    void deleteOrder() {
        // Arrange
        Long orderId = 1L;
        this.orderRepository.save(createTestOrder(orderId));
        // Act
        this.orderService.deleteOrder(orderId);
        // Assert
        Order deletedOrder = this.orderRepository.findById(orderId).orElse(null);
        assertNull(deletedOrder);
    }
}