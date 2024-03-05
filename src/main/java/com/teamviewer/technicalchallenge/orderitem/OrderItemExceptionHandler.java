package com.teamviewer.technicalchallenge.orderitem;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrderItemExceptionHandler {

    @ResponseBody
    @ExceptionHandler(OrderItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String orderItemNotFoundHandler(OrderItemNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ExistingOrderItemException.class)
    String orderItemAlreadyExists(ExistingOrderItemException ex) {
        return ex.getMessage();
    }
}
