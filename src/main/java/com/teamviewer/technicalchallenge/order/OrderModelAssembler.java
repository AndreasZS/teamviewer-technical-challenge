package com.teamviewer.technicalchallenge.order;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// TODO: pick up from adding status logic to OrderModelAssembler
@Component
public class OrderModelAssembler implements RepresentationModelAssembler<Order, EntityModel<Order>> {
    @Override
    public EntityModel<com.teamviewer.technicalchallenge.order.Order> toModel(@NonNull Order order) {
        return EntityModel.of(order,
                linkTo(methodOn(OrderController.class).getOrder(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).getAllOrders()).withRel("/orders"));
    }

    @Override
    public CollectionModel<EntityModel<com.teamviewer.technicalchallenge.order.Order>> toCollectionModel(@NonNull Iterable<? extends com.teamviewer.technicalchallenge.order.Order> orders) {
        return RepresentationModelAssembler.super.toCollectionModel(orders);
    }
}
