package com.clearsolutions.testapp.userMapper;

import com.clearsolutions.testapp.dto.UserDto;
import com.clearsolutions.testapp.models.Users;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    @Test
    void whenConvertUsersEntityToUserDto_thenCorrect() {
        Users user = new Users();
        user.setEmail("testemail@gmail.com");
        user.setFirstName("John");
        user.setLastName("Wick");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setAddress("Ukraine");
        user.setPhoneNumber("+380501111111");

        UserDto userDto = UserMapper.convertToDto(user);
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getBirthDate(), userDto.getBirthDate());
        assertEquals(user.getAddress(), userDto.getAddress());
        assertEquals(user.getPhoneNumber(), userDto.getPhoneNumber());

    }

    @Test
    void whenConvertUserDtoToUserEntity_thenCorrect() {
        UserDto userDto = new UserDto();
        userDto.setEmail("testemail@gmail.com");
        userDto.setFirstName("John");
        userDto.setLastName("Wick");
        userDto.setBirthDate(LocalDate.of(1990, 1, 1));
        userDto.setAddress("Ukraine");
        userDto.setPhoneNumber("+380501111111");

        Users user = UserMapper.convertToEntity(userDto);
        assertEquals(userDto.getEmail(), user.getEmail());
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
        assertEquals(userDto.getBirthDate(), user.getBirthDate());
        assertEquals(userDto.getAddress(), user.getAddress());
        assertEquals(userDto.getPhoneNumber(), user.getPhoneNumber());
    }
}