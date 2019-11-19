package com.petservice.backend.controllers;

import com.petservice.backend.services.exceptions.FileUploadException;
import com.petservice.backend.services.exceptions.NotFoundException;
import com.petservice.backend.services.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<String> notFoundException(NotFoundException exception){
        log.debug(exception.toString(), exception);
        return new ResponseEntity<>(exception.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = FileUploadException.class)
    public ResponseEntity<String> fileUploadException(FileUploadException exception){
        log.debug(exception.toString(), exception);
        return new ResponseEntity<>(exception.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<String> validationException(ValidationException exception) {
        log.debug(exception.toString(), exception);
        return new ResponseEntity<>(exception.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> globalException(RuntimeException exception) {
        log.debug(exception.toString(), exception);
        return new ResponseEntity<>(exception.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
