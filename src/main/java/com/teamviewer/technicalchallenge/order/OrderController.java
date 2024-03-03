package com.teamviewer.technicalchallenge.order;

import com.teamviewer.technicalchallenge.order.Order;
import com.teamviewer.technicalchallenge.order.OrderModelAssembler;
import com.teamviewer.technicalchallenge.order.OrderNotFoundException;
import com.teamviewer.technicalchallenge.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderModelAssembler assembler;

    @Autowired
    OrderController(OrderService orderService, OrderModelAssembler assembler) {
        this.orderService = orderService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Order>> getAllOrders() {
//        List<EntityModel<Order>> orders =
//                this.orderService.getAllOrders().stream()
//                        .map(assembler::toModel)
//                        .toList();
//        return CollectionModel.of(orders,
//                linkTo(methodOn(OrderController.class).getAllOrders()).withSelfRel());
        List<Order> orders = this.orderService.getAllOrders();
        return assembler.toCollectionModel(orders);
    }

    @GetMapping("/{id}")
    public EntityModel<Order> getOrder(@PathVariable Long id) {
        Order order = this.orderService.getOrder(id).orElseThrow(() -> new OrderNotFoundException(id));
        return assembler.toModel(order);
    }

    @PostMapping
    public ResponseEntity<EntityModel<com.teamviewer.technicalchallenge.order.Order>> createOrder(Order newOrder) {
        EntityModel<com.teamviewer.technicalchallenge.order.Order> entityModel = assembler.toModel(this.orderService.createOrder(newOrder));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<com.teamviewer.technicalchallenge.order.Order>> updateOrder(@RequestBody Order newOrder, @PathVariable Long id) {
        EntityModel<Order> entityModel = assembler.toModel(this.orderService.updateOrder(id, newOrder));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{id}")
    public void deleteMapping(@PathVariable Long id) {
        this.orderService.deleteOrder(id);
    }

}
