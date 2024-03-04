package com.teamviewer.technicalchallenge.orderitem;

import com.teamviewer.technicalchallenge.exception.NotFoundException;

public class OrderItemNotFoundException extends NotFoundException {

    OrderItemNotFoundException(Long id) {
        super(OrderItem.class, id);
    }
}
