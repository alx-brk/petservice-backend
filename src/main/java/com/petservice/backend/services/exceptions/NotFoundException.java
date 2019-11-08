package com.petservice.backend.services.exceptions;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
public class NotFoundException extends RuntimeException {
    private Class entity;
    private Object object;
    private String message;
    private Throwable throwable;
}
