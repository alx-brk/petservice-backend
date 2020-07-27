package com.petservice.backend.services.validation;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Validator<T> {
    private T entity;
    private String entityName;

    private Validator(T entity) {
        this.entity = Objects.requireNonNull(entity);
        this.entityName = entity.getClass().getSimpleName();
    }

    public static <T> Validator<T> of(T t) {
        return new Validator<>(t);
    }

    public <U> Validator<T> validateCondition(Function<T, U> field, Predicate<U> condition, RuntimeException ex) {
        if (!condition.test(field.apply(entity))){
            throw ex;
        }
        return this;
    }

    public <U> Validator<T> validateNotNull(Function<T, U> field, RuntimeException ex) {
        if (!Objects.nonNull(field.apply(entity))){
            throw ex;
        }
        return this;
    }
}
