package com.clearsolutions.testapp.controllers;

import com.clearsolutions.testapp.models.Users;
import com.clearsolutions.testapp.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static com.clearsolutions.testapp.utils.JsonReaderUtils.readJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void before() {
        userRepository.deleteAll();
        userRepository.flush();

        generateTestUsers();
    }

    @Test
    void whenCreateUser_thenStatus200() throws Exception {
        this.mockMvc
                .perform(put("/users/testemail@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("json/case1_create_user.json")))
                .andExpect(status().isOk());
    }

    @Test
    void whenIncorrectEmail_thenStatus400() throws Exception {
        this.mockMvc
                .perform(put("/users/testemailgmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("json/case2_create_with_incorrect_email.json")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenDateOfBirthAfterCurrentDate_thenStatus400() throws Exception {
        this.mockMvc
                .perform(put("/users/testemail@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("json/case3_create_with_incorrect_birthdate.json")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenUserAgeLessThan18_thenStatus400() throws Exception {
        this.mockMvc
                .perform(put("/users/testemail@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("json/case4_create_with_user_age_less_than_18.json")))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenUpdateAllFieldsOfUser_thenStatus200() throws Exception {
        this.mockMvc
                .perform(put("/users/testemail@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("json/case5_update_all_fields_of_user.json")))
                .andExpect(status().isOk());
    }

    @Test
    void whenUpdateSomeFieldsOfUser_thenStatus200() throws Exception {
        this.mockMvc
                .perform(patch("/users/testemail1@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("json/case6_update_some_fields_of_user.json")))
                .andExpect(status().isOk());
    }

    @Test
    void whenUpdateSomeFieldsOfUserAndWriteIncorrectEmail_thenStatus400() throws Exception {
        this.mockMvc
                .perform(patch("/users/testemail1gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("json/case6_update_some_fields_of_user.json")))
                .andExpect(status().isBadRequest());
    }


    @Test
    void whenDeleteUser_thenStatus200() throws Exception {
        this.mockMvc
                .perform(delete("/users/testemail1@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("json/case7_delete_user.json")))
                .andExpect(status().isOk());
    }

    @Test
    void whenDeleteUserWhoDoesNotExist_thenStatus404() throws Exception {
        this.mockMvc
                .perform(delete("/users/test2email@gmail.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(readJson("json/case8_delete_user_who_does_not_exist.json")))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenFindUser_thenStatus200() throws Exception {
        var respBody = this.mockMvc
                .perform(get("/users")
                        .param("birthDateFrom", String.valueOf(LocalDate.of(1988,1,1)))
                        .param("birthDateTo", String.valueOf(LocalDate.of(2020,1,1))))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    private void generateTestUsers() {
        Users user1 = new Users();
        user1.setEmail("testemail1@gmail.com");
        user1.setFirstName("John1");
        user1.setLastName("Wick1");
        user1.setBirthDate(LocalDate.of(1990, 1, 1));
        user1.setAddress("Ukraine1");
        user1.setPhoneNumber("+380501111111");
        userRepository.save(user1);

        Users user2 = new Users();
        user2.setEmail("testemail2@gmail.com");
        user2.setFirstName("John2");
        user2.setLastName("Wick2");
        user2.setBirthDate(LocalDate.of(2021, 1, 1));
        user2.setAddress("Ukraine2");
        user2.setPhoneNumber("+380501111112");
        userRepository.save(user2);

        Users user3 = new Users();
        user3.setEmail("testemail3@gmail.com");
        user3.setFirstName("John3");
        user3.setLastName("Wick3");
        user3.setBirthDate(LocalDate.of(1995, 1, 1));
        user3.setAddress("Ukraine3");
        user3.setPhoneNumber("+380501111113");
        userRepository.save(user3);

        userRepository.flush();
    }
}