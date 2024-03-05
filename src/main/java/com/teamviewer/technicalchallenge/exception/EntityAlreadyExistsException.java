package com.teamviewer.technicalchallenge.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(Class<?> clazz, Long id) {
        super(String.format("%s with ID: %s already exists. Did you mean to update the existing %1$s?",
                clazz.getSimpleName(), id));
    }
}
