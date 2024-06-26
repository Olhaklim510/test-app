package com.clearsolutions.testapp;

import com.clearsolutions.testapp.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
		assertThat(userController).isNotNull();
	}

}
