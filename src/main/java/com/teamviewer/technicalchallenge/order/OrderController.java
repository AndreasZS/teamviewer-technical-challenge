package com.teamviewer.technicalchallenge.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Get a list of all orders.
     * @return ResponseEntity containing list of all orders
     */
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = this.orderService.getOrders();
        return ResponseEntity.ok().body(orders);
    }

    /**
     * Get an order by ID.
     * @param id Order ID
     * @return ResponseEntity containing order
     */
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = this.orderService.getOrder(id);
        return ResponseEntity.ok().body(order);
    }

    /**
     * Create a new order.
     * @param newOrder new Order to create
     * @return ResponseEntity containing order if it was created successfully
     */
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order newOrder) {
        Order createdOrder = this.orderService.createOrder(newOrder);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdOrder.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdOrder);
    }

    /**
     * Update an existing order.
     * @param newOrder new Order containing updated fields
     * @param id ID of order to update
     * @return ResponseEntity containing updated Order
     */
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order newOrder, @PathVariable Long id) {
        Order updatedOrder = this.orderService.updateOrder(id, newOrder);
        return ResponseEntity.ok().body(updatedOrder);
    }

    /**
     * Delete an order by ID.
     * @param id ID of order to delete
     */
    @DeleteMapping("/orders/{id}")
    public void deleteMapping(@PathVariable Long id) {
        this.orderService.deleteOrder(id);
    }

}
