package com.teamviewer.technicalchallenge.orderitem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    public void testFindAll() {
        // Arrange
        OrderItem orderItem1 = new OrderItem(1L, 0, null, null);
        OrderItem orderItem2 = new OrderItem(2L, 0, null, null);
        this.orderItemRepository.saveAll(List.of(orderItem1, orderItem2));
        // Act
        List<OrderItem> orderItems = this.orderItemRepository.findAll();
        // Assert
        assertEquals(2, orderItems.size());
        assertTrue(orderItems.contains(orderItem1));
        assertTrue(orderItems.contains(orderItem2));

    }

    @Test
    public void testFindById() {
        // Arrange
        OrderItem orderItem1 = new OrderItem(1L, 0, null, null);
        this.orderItemRepository.saveAndFlush(orderItem1);
        // Act
        OrderItem orderItem = this.orderItemRepository.findById(1L).orElse(null);
        // Assert
        assertEquals(orderItem1, orderItem);
    }
}