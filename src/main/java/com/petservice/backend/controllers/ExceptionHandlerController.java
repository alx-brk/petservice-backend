package com.petservice.backend.controllers;

import com.petservice.backend.services.exceptions.AuthException;
import com.petservice.backend.services.exceptions.FileUploadException;
import com.petservice.backend.services.exceptions.NotFoundException;
import com.petservice.backend.services.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handleAuthException(AuthException exception){
        log.debug(exception.toString(), exception);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return new ResponseEntity<>(exception.toString(), HttpStatus.UNAUTHORIZED);
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
        return new ResponseEntity<>("Don't panic!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
