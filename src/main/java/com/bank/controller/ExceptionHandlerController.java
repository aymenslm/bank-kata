package com.bank.controller;


import com.bank.exception.ErrorResponse;
import com.bank.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerController {
    private static final String ERROR_NOT_FOUND = "404";

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse requestHandlingNoHandlerFound(RecordNotFoundException e) {
        return new ErrorResponse(ERROR_NOT_FOUND, e.getMessage());
    }
}
