package com.teamviewer.technicalchallenge.product;

import com.teamviewer.technicalchallenge.exception.EntityAlreadyExistsException;

public class ExistingProductException extends EntityAlreadyExistsException {

    public ExistingProductException(Long id) {
        super(Product.class, id);
    }
}
