package com.petservice.backend.controllers;

import com.petservice.backend.services.exceptions.*;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> handleJwtException(JwtException exception){
        log.debug(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.toString(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<String> handleJwtException(ForbiddenException exception){
        log.debug(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.toString(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException exception){
        log.debug(exception.toString(), exception);
        return new ResponseEntity<>(exception.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<String> handleFileUploadException(FileUploadException exception){
        log.debug(exception.toString(), exception);
        return new ResponseEntity<>(exception.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException exception) {
        log.debug(exception.toString(), exception);
        return new ResponseEntity<>(exception.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
        log.debug(exception.toString(), exception);
        return new ResponseEntity<>("Don't panic!\n" + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
