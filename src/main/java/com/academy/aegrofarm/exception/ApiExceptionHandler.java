package com.academy.aegrofarm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ObjectNotFoundException.class})
    public ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException e) {
        HttpStatus NotFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(e.getMessage(),
                NotFound,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, NotFound);
    }

    @ExceptionHandler(value = {InvalidOperationException.class})
    public ResponseEntity<Object> handleInvalidOperationException(InvalidOperationException e) {
        HttpStatus Unprocessable = HttpStatus.UNPROCESSABLE_ENTITY;
        ApiException apiException = new ApiException(e.getMessage(),
                Unprocessable,
                ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, Unprocessable);
    }

}
