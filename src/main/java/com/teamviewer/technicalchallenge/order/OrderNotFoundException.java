package com.teamviewer.technicalchallenge.order;

import com.teamviewer.technicalchallenge.exception.NotFoundException;

public class OrderNotFoundException extends NotFoundException {

    OrderNotFoundException(Long id) {
        super(Order.class, id);
    }
}
