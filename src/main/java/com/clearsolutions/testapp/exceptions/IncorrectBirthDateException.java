package com.clearsolutions.testapp.exceptions;

import java.time.LocalDate;

public class IncorrectBirthDateException extends RuntimeException{
    public IncorrectBirthDateException(LocalDate birthDate) {
        super("BirthDate must be earlier than current date " + birthDate);
    }
}
