package com.clearsolutions.testapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MinAgeAdvice {
    @ResponseBody
    @ExceptionHandler(MinAgeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String MinAgeHandler(MinAgeException exception) {
        return exception.getMessage();
    }
}
