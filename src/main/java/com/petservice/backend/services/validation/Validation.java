package com.petservice.backend.services.validation;

public interface Validation<T> {

    void validateOnCreate (T entity);
    void validateOnUpdate (T entity);
}
