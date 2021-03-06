package com.petservice.backend.services.exceptions;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
public class NotFoundException extends RuntimeException {
    private Class entity;
    private Object object;
    private String message;
    private Throwable throwable;
}
