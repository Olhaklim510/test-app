package com.clearsolutions.testapp.validators;

import com.clearsolutions.testapp.exceptions.IncorrectBirthDateException;
import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Component
public class DataValidators {
    public void isDataValid(LocalDate birthDateFrom, LocalDate birthDateTo) {
        if (birthDateTo.isBefore(birthDateFrom)) {
            throw new IncorrectBirthDateException(birthDateFrom);
        }
    }
}
