package com.clearsolutions.testapp.exceptions;

public class IncorrectEmailException extends RuntimeException{
    public IncorrectEmailException(String email) {
        super("Email has incorrect format " + email);
    }
}
