package com.teamviewer.technicalchallenge.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> clazz, Long id) {
        super(String.format("Could not find %s %s", clazz.getSimpleName(), id));
    }
}
