package com.teamviewer.technicalchallenge.order;

import com.teamviewer.technicalchallenge.exception.EntityAlreadyExistsException;
import com.teamviewer.technicalchallenge.orderitem.OrderItem;

public class ExistingOrderException extends EntityAlreadyExistsException {

    public ExistingOrderException(Long id) {
        super(Order.class, id);
    }
}
