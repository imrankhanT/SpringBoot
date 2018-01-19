package com.bridgeit;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bridgeit.controller.LoginController;
import com.bridgeit.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginSpringBootTest {

	@InjectMocks
	LoginController loginController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	@org.junit.Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testLoginEmployee() throws Exception {
		String message = "Login Sucessfully.......";
		Employee employee = new Employee();
		employee.setEmail("imterdal@gmail.com");
		employee.setPassword("qwertyuio");
		this.mvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonObject(employee))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(message)).andDo(print());
	}

	@Test
	public void testLoginFail() throws Exception {
		String message = "Invalid Emial_Id Or password......";
		Employee employee = new Employee();
		employee.setEmail("imterdal@gmail.com");
		employee.setPassword("quuiyurweqrw");
		this.mvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonObject(employee))).andExpect(status().isBadRequest())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(message)).andDo(print());
	}

	private String jsonObject(Employee employee) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		jsonString = mapper.writeValueAsString(employee);
		return jsonString;
	}
}
