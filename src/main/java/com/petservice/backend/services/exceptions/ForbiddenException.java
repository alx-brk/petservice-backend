package com.petservice.backend.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ForbiddenException extends RuntimeException {
    private String message;
}
