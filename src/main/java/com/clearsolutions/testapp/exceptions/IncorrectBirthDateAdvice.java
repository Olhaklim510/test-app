package com.clearsolutions.testapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class IncorrectBirthDateAdvice {
    @ResponseBody
    @ExceptionHandler(IncorrectBirthDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String incorrectBirthDateHandler(IncorrectBirthDateException exception) {
        return exception.getMessage();
    }
}
