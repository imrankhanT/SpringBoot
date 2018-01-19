package com.bridgeit;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bridgeit.controller.UserController;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSpringBootTest {

	@InjectMocks
	UserController userController;

	@Autowired
	private WebApplicationContext web;

	private MockMvc mvc;

	@org.junit.Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(web).build();
	}

	@Test
	public void userTestPass() throws Exception {
		int id = 1;
		String message = "User Present In The DataBase....";
		this.mvc.perform(MockMvcRequestBuilders.get("/getUserById/id", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(message)).andExpect(status().isOk())
				.andDo(print());
	}

}
