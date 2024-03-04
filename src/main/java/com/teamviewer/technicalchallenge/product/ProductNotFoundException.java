package com.teamviewer.technicalchallenge.product;

import com.teamviewer.technicalchallenge.exception.NotFoundException;

public class ProductNotFoundException extends NotFoundException {

    ProductNotFoundException(Long id) {
        super(Product.class, id);
    }
}
