package com.petservice.backend.services.exceptions;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
public class AuthException extends RuntimeException {
    private String message;
    private Throwable throwable;
}
