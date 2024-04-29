package com.clearsolutions.testapp.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email) {
        super("Could not find user " + email);
    }
}
