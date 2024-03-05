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

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = this.orderService.getOrders();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        Order order = this.orderService.getOrder(id);
        return ResponseEntity.ok().body(order);
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order newOrder) {
        Order createdOrder = this.orderService.createOrder(newOrder);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdOrder.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdOrder);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order newOrder, @PathVariable Long id) {
        Order updatedOrder = this.orderService.updateOrder(id, newOrder);
        return ResponseEntity.ok().body(updatedOrder);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteMapping(@PathVariable Long id) {
        this.orderService.deleteOrder(id);
    }

}
