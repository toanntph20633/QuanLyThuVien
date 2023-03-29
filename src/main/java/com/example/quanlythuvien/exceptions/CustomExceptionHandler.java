package com.example.quanlythuvien.exceptions;

import com.example.quanlythuvien.exceptions.exception.BadRequestException;
import com.example.quanlythuvien.exceptions.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Errors notFoundException(NotFoundException e) {
        return new Errors(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Errors badRequestException(BadRequestException e) {
        return new Errors(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
