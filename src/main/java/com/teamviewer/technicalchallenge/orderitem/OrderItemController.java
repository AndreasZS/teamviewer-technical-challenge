package com.teamviewer.technicalchallenge.orderitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }


    /**
     * Get a list of all order items.
     * @return ResponseEntity containing list of all order items
     */
    @GetMapping("/order-items")
    public ResponseEntity<List<OrderItem>> getOrderItems() {
        List<OrderItem> orderItems = this.orderItemService.getOrderItems();
        return ResponseEntity.ok().body(orderItems);
    }

    /**
     * Get an order item by ID.
     * @param id OrderItem ID
     * @return ResponseEntity containing order item
     */
    @GetMapping("/order-items/{id}")
    public ResponseEntity<OrderItem> getOrderItem(@PathVariable Long id) {
        OrderItem orderItem = this.orderItemService.getOrderItem(id);
        return ResponseEntity.ok().body(orderItem);
    }

    /**
     * Create a new order item.
     * @param newOrderItem new OrderItem to create
     * @return ResponseEntity containing order item if it was created successfully
     */
    @PostMapping("/order-items")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItem newOrderItem) {
        OrderItem createdOrderItem = this.orderItemService.createOrderItem(newOrderItem);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdOrderItem.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdOrderItem);
    }

    /**
     * Update an existing order item.
     * @param newOrderItem new OrderItem containing updated fields
     * @param id ID of order item to update
     * @return ResponseEntity containing updated order item
     */
    @PutMapping("/order-items/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@RequestBody OrderItem newOrderItem, @PathVariable Long id) {
        OrderItem updatedOrderItem = this.orderItemService.updateOrderItem(id, newOrderItem);
        return ResponseEntity.ok().body(updatedOrderItem);
    }

    /**
     * Delete an order item by ID.
     * @param id ID of order item to delete
     */
    @DeleteMapping("/order-items/{id}")
    public void deleteMapping(@PathVariable Long id) {
        this.orderItemService.deleteOrderItem(id);
    }
}
