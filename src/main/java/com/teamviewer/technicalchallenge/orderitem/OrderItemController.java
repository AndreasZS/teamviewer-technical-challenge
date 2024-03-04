package com.teamviewer.technicalchallenge.orderitem;

import com.teamviewer.technicalchallenge.orderitem.OrderItem;
import com.teamviewer.technicalchallenge.orderitem.OrderItemService;
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


    @GetMapping("/order-items")
    public ResponseEntity<List<OrderItem>> getOrderItems() {
        List<OrderItem> orderItems = this.orderItemService.getOrderItems();
        return ResponseEntity.ok().body(orderItems);
    }

    @GetMapping("/order-items/{id}")
    public ResponseEntity<OrderItem> getOrderItem(@PathVariable Long id) {
        OrderItem orderItem = this.orderItemService.getOrderItem(id);
        return ResponseEntity.ok().body(orderItem);
    }

    @PostMapping("/order-items")
    public ResponseEntity<OrderItem> createOrderItem(OrderItem newOrderItem) {
        OrderItem createdOrderItem = this.orderItemService.createOrderItem(newOrderItem);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdOrderItem.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdOrderItem);
    }

    @PutMapping("/order-items/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@RequestBody OrderItem newOrderItem, @PathVariable Long id) {
        OrderItem updatedOrderItem = this.orderItemService.updateOrderItem(id, newOrderItem);
        return ResponseEntity.ok().body(updatedOrderItem);
    }

    @DeleteMapping("/order-items/{id}")
    public void deleteMapping(@PathVariable Long id) {
        this.orderItemService.deleteOrderItem(id);
    }
}
