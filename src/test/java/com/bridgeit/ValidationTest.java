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

import com.bridgeit.controller.RegisterController;
import com.bridgeit.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ValidationTest {

	@InjectMocks
	RegisterController registerController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	@org.junit.Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testRegisterEmail() throws Exception {
		String message = "Invalid Email";
		Employee employee = new Employee();
		employee.setEmail("admadfsd.com");
		employee.setPhone("5678909867");
		employee.setEmpName("Adam V");
		employee.setPassword("qwer");
		this.mvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonObject(employee))).andExpect(MockMvcResultMatchers.jsonPath("$.message").value(message))
				.andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void testRegisterPhone() throws Exception {
		String message = "Phone Number Should Be 10 Digit......";
		Employee employee = new Employee();
		employee.setEmpName("adam V");
		employee.setEmail("adam@gmail.com");
		employee.setPassword("123456789");
		employee.setPhone("6786786867878678678678688");
		this.mvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonObject(employee))).andExpect(MockMvcResultMatchers.jsonPath("$.message").value(message))
				.andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void testRegisterPassword() throws Exception {
		String message = "Please! Enter The Password between 7 to 16 charcters......";
		Employee employee = new Employee();
		employee.setEmail("adam@gmail.com");
		employee.setEmpName("adam V");
		employee.setPassword("123");
		employee.setPhone("7896058697");
		this.mvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonObject(employee))).andExpect(MockMvcResultMatchers.jsonPath("$.message").value(message))
				.andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void testRegisterName() throws Exception {
		String message = "Name To Short.......";
		Employee employee = new Employee();
		employee.setEmpName("a");
		employee.setEmail("adam@gmail.com");
		employee.setPassword("123456789");
		employee.setPhone("7896058697");
		this.mvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonObject(employee))).andExpect(MockMvcResultMatchers.jsonPath("$.message").value(message))
				.andExpect(status().isBadRequest()).andDo(print());
	}

	private String jsonObject(Employee employee) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		jsonString = mapper.writeValueAsString(employee);
		return jsonString;
	}
}
