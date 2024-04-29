package com.clearsolutions.testapp.userMapper;

import com.clearsolutions.testapp.dto.UserDto;
import com.clearsolutions.testapp.models.Users;


public class UserMapper {

    public static UserDto convertToDto(Users user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setAddress(user.getAddress());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }

    public static Users convertToEntity(UserDto userDto) {
        Users user=new Users();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBirthDate(userDto.getBirthDate());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return user;
    }
}
