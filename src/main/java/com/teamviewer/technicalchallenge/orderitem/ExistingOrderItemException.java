package com.teamviewer.technicalchallenge.orderitem;

import com.teamviewer.technicalchallenge.exception.EntityAlreadyExistsException;

public class ExistingOrderItemException extends EntityAlreadyExistsException {

    public ExistingOrderItemException(Long id) {
        super(OrderItem.class, id);
    }
}
