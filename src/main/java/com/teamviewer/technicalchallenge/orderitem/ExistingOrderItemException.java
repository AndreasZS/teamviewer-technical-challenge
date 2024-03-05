package com.teamviewer.technicalchallenge.orderitem;

import com.teamviewer.technicalchallenge.exception.EntityAlreadyExistsException;
import com.teamviewer.technicalchallenge.product.Product;

public class ExistingOrderItemException extends EntityAlreadyExistsException {

    public ExistingOrderItemException(Long id) {
        super(OrderItem.class, id);
    }
}
