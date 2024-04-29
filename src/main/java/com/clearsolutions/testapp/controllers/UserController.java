package com.clearsolutions.testapp.controllers;

import com.clearsolutions.testapp.dto.UserDto;
import com.clearsolutions.testapp.services.UserServiceImpl;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(@Autowired UserServiceImpl userService) {
        this.userService = userService;
    }

    @PutMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody UserDto userDto, @PathVariable String email) {
        userService.createOrUpdateUser(userDto);
    }

    @PatchMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody JsonPatch patch, @PathVariable String email) throws Exception {
        userService.updateSomeUserFields(patch, email);
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String email) {
        userService.deleteUserByEmail(email);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> search(@RequestParam LocalDate birthDateFrom, @RequestParam LocalDate birthDateTo) {
        return userService.searchUsersByBirthDataRange(birthDateFrom, birthDateTo);
    }
}
