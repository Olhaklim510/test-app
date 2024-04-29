package com.clearsolutions.testapp.services;

import com.clearsolutions.testapp.dto.UserDto;
import com.clearsolutions.testapp.exceptions.IncorrectEmailException;
import com.clearsolutions.testapp.exceptions.UserNotFoundException;
import com.clearsolutions.testapp.models.Users;
import com.clearsolutions.testapp.repositories.UserRepository;
import com.clearsolutions.testapp.userMapper.UserMapper;
import com.clearsolutions.testapp.validators.DataValidators;
import com.clearsolutions.testapp.validators.UserValidators;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.fge.jsonpatch.JsonPatch;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserValidators userValidators;
    private final DataValidators dataValidators;

    public UserServiceImpl(@Autowired UserRepository userRepository,
                           @Autowired UserValidators userValidators,
                           @Autowired DataValidators dataValidators) {
        this.userRepository = userRepository;
        this.userValidators = userValidators;
        this.dataValidators = dataValidators;
    }

    @Override
    public void createOrUpdateUser(UserDto userDto) {
        userValidators.userValidation(userDto);
        userRepository.save(UserMapper.convertToEntity(userDto));
    }

    @Override
    public void updateSomeUserFields(final JsonPatch patch, final String email) throws Exception {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IncorrectEmailException(email);
        }

        Optional<Users> usersByIdOptional = userRepository.findById(email);

        if (usersByIdOptional.isEmpty()) {
            throw new UserNotFoundException(email);
        }

        Users user = usersByIdOptional.get();

        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        JsonNode patched = patch.apply(mapper.convertValue(user, JsonNode.class));
        Users patchedUser = mapper.treeToValue(patched, Users.class);

        userRepository.saveAndFlush(patchedUser);
    }


    @Override
    public void deleteUserByEmail(String email) {
        if (!userRepository.existsById(email)) {
            throw new UserNotFoundException(email);
        }
        userRepository.deleteById(email);
    }

    @Override
    public List<UserDto> searchUsersByBirthDataRange(LocalDate birthDateFrom, LocalDate birthDateTo) {
        dataValidators.isDataValid(birthDateFrom, birthDateTo);
        return userRepository
                .findByBirthDateBetween(birthDateFrom, birthDateTo)
                .stream().map(UserMapper::convertToDto).toList();
    }
}

