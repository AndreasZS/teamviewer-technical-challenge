package com.teamviewer.technicalchallenge.order;

import com.teamviewer.technicalchallenge.order.Order;
import com.teamviewer.technicalchallenge.order.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
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

    /*@Test
    public void testFindById() {
        // Fails if testFindAll runs first, since the ID of the new order will actually be 3L
        // Arrange
        this.orderRepository.deleteAll();
        Order order1 = new Order(1L, "Order 1");
        this.orderRepository.saveAndFlush(order1);
        // Act
        Order order = this.orderRepository.findById(1L).orElse(null);
        // Assert
        assertEquals(order1, order);
    }*/
}