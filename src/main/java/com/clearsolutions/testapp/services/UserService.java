package com.clearsolutions.testapp.services;

import com.clearsolutions.testapp.dto.UserDto;
import com.github.fge.jsonpatch.JsonPatch;
import java.time.LocalDate;
import java.util.List;

public interface UserService {

    void createOrUpdateUser(final UserDto userDto);

    void updateSomeUserFields(final JsonPatch patch, final String email) throws Exception;

    void deleteUserByEmail(final String email);

    List<UserDto> searchUsersByBirthDataRange(final LocalDate birthDateFrom, final LocalDate birtDateTo);
}
