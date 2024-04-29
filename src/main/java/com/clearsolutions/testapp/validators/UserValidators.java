package com.clearsolutions.testapp.validators;

import com.clearsolutions.testapp.dto.UserDto;
import com.clearsolutions.testapp.exceptions.IncorrectBirthDateException;
import com.clearsolutions.testapp.exceptions.IncorrectEmailException;
import com.clearsolutions.testapp.exceptions.MinAgeException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Component
public class UserValidators {
    private final Integer userMinAge;

    public UserValidators(@Value("${user.min.age}") Integer userMinAge) {
        this.userMinAge = userMinAge;
    }

    public void userValidation(UserDto userDto) {
        if (!EmailValidator.getInstance().isValid(userDto.getEmail())) {
            throw new IncorrectEmailException(userDto.getEmail());
        }
        if (LocalDate.now().isBefore(userDto.getBirthDate())) {
            throw new IncorrectBirthDateException(userDto.getBirthDate());
        }
        if (ChronoUnit.YEARS.between(userDto.getBirthDate(),LocalDate.now()) < userMinAge) {
            throw new MinAgeException(userDto.getBirthDate());
        }
    }
}
