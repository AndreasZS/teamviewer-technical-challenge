package com.teamviewer.technicalchallenge.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testFindAll() {
        // Arrange
        Order order1 = new Order(1L, null, null, Status.IN_PROGRESS, null);
        Order order2 = new Order(2L, null, null, Status.IN_PROGRESS, null);
        this.orderRepository.saveAll(List.of(order1, order2));
        // Act
        List<Order> orders = this.orderRepository.findAll();
        // Assert
        assertEquals(2, orders.size());
    }

    @Test
    public void testFindById() {
        // Arrange
        this.orderRepository.deleteAll();
        Order order1 = new Order(1L, null, null, Status.IN_PROGRESS, null);
        this.orderRepository.saveAndFlush(order1);
        // Act
        Order order = this.orderRepository.findById(1L).orElse(null);
        // Assert
        assertEquals(order1, order);
    }
}