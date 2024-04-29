package com.clearsolutions.testapp.exceptions;

import java.time.LocalDate;

public class MinAgeException extends RuntimeException{
    public MinAgeException(LocalDate birthDate) {
        super("It allows to register users who are more than 18 years old ");
    }
}
