package com.teamviewer.technicalchallenge.order;

import com.teamviewer.technicalchallenge.order.Order;
import com.teamviewer.technicalchallenge.order.OrderRepository;
import com.teamviewer.technicalchallenge.order.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
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
    void getOrders() {
        // Arrange
        when(this.orderRepository.findAll())
                .thenReturn(List.of(createTestOrder(1L),
                        createTestOrder(2L)));
        // Act
        List<Order> orders = this.orderService.getOrders();
        // Assert
        verify(this.orderRepository, times(1)).findAll();
    }

    @Test
    void getOrder() {
        // Arrange
        Long orderId = 1L;
        when(this.orderRepository.findById(orderId))
                .thenReturn(Optional.of(new Order(1L, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()),
                        Status.IN_PROGRESS, List.of())));
        // Act
        Order order = this.orderService.getOrder(orderId);
        // Assert
        verify(this.orderRepository, times(1)).findById(orderId);
    }

    @Test
    void createOrder() {
        // Arrange
        Order newOrder = new Order(null, null, null, Status.IN_PROGRESS, null);
        when(this.orderRepository.save(newOrder))
                .thenReturn(new Order(1L, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()),
                 Status.IN_PROGRESS, List.of()));
        // Act
        Order createdOrder = this.orderService.createOrder(newOrder);
        // Assert
        verify(this.orderRepository, times(1)).save(newOrder);
    }

    @Test
    void updateOrder() {
        // Arrange
        Long orderId = 1L;
        Order existingOrder = new Order(orderId, null, null, Status.IN_PROGRESS, null);
        when(this.orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        Order updatedOrder = new Order(orderId, null, null, Status.COMPLETED, List.of());
        when(this.orderRepository.save(updatedOrder)).thenReturn(updatedOrder);
        // Act
        Order order = this.orderService.updateOrder(orderId, updatedOrder);
        // Assert
        verify(this.orderRepository, times(1)).save(updatedOrder);
    }

    @Test
    void deleteOrder() {
        // Arrange
        Long orderId = 1L;
        when(this.orderRepository.existsById(orderId)).thenReturn(true);
        // Act
        this.orderService.deleteOrder(orderId);
        // Assert
        verify(this.orderRepository, times(1)).deleteById(orderId);
    }
}