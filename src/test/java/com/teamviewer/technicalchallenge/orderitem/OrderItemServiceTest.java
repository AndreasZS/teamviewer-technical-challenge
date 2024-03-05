package com.teamviewer.technicalchallenge.orderitem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class OrderItemServiceTest {

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
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
    void getOrderItems() {
        // Arrange
        when(this.orderItemRepository.findAll())
                .thenReturn(List.of(createTestOrderItem(1L),
                        createTestOrderItem(2L)));
        // Act
        List<OrderItem> orderItems = this.orderItemService.getOrderItems();
        // Assert
        verify(this.orderItemRepository, times(1)).findAll();
    }

    @Test
    void getOrderItem() {
        // Arrange
        Long orderItemId = 1L;
        when(this.orderItemRepository.findById(orderItemId))
                .thenReturn(Optional.of(createTestOrderItem(orderItemId)));
        // Act
        OrderItem orderItem = this.orderItemService.getOrderItem(orderItemId);
        // Assert
        verify(this.orderItemRepository, times(1)).findById(orderItemId);
    }

    @Test
    void createOrderItem() {
        // Arrange
        OrderItem newOrderItem = createTestOrderItem(1L);
        when(this.orderItemRepository.save(newOrderItem))
                .thenReturn(createTestOrderItem(1L));
        // Act
        OrderItem createdOrderItem = this.orderItemService.createOrderItem(newOrderItem);
        // Assert
        verify(this.orderItemRepository, times(1)).save(newOrderItem);
    }

    @Test
    void updateOrderItem() {
        // Arrange
        Long orderItemId = 1L;
        OrderItem existingOrderItem = createTestOrderItem(orderItemId);
        when(this.orderItemRepository.findById(orderItemId)).thenReturn(Optional.of(existingOrderItem));
        OrderItem updatedOrderItem = new OrderItem(orderItemId, 1, null, null);
        when(this.orderItemRepository.save(updatedOrderItem)).thenReturn(updatedOrderItem);
        // Act
        OrderItem orderItem = this.orderItemService.updateOrderItem(orderItemId, updatedOrderItem);
        // Assert
        verify(this.orderItemRepository, times(1)).save(updatedOrderItem);
    }

    @Test
    void deleteOrderItem() {
        // Arrange
        Long orderItemId = 1L;
        when(this.orderItemRepository.existsById(orderItemId)).thenReturn(true);
        // Act
        this.orderItemService.deleteOrderItem(orderItemId);
        // Assert
        verify(this.orderItemRepository, times(1)).deleteById(orderItemId);
    }
}