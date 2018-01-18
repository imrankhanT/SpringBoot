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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bridgeit.controller.HomeController;
import com.bridgeit.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestSpringBoot {

	@InjectMocks
	HomeController homeController;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mvc;

	@org.junit.Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testLoginEmployee() {
		Employee employee = new Employee();
		employee.setEmail("imterdal@gmail.com");
		employee.setPassword("qwertyuio");
		System.out.println(homeController);
		try {
			this.mvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
					.content(jsonObject(employee))).andExpect(status().isOk()).andDo(print());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLoginFail() throws Exception {
		Employee employee = new Employee();
		employee.setEmail("imterdal@gmail.com");
		employee.setPassword("quuiyurweqrw");
		this.mvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonObject(employee))).andExpect(status().isBadRequest()).andDo(print());
	}

	private String jsonObject(Employee employee) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(employee);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}

	@Test
	public void testRegisterEmployee() throws Exception {
		Employee employee = new Employee();
		employee.setEmpName("AdamVoges");
		employee.setEmail("voges@gmail.com");
		employee.setPassword("qwertyio");
		employee.setPhone("7856748756");
		this.mvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonObject(employee))).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testRegisterExsists() throws Exception {
		Employee employee = new Employee();
		employee.setEmpName("Imran khan");
		employee.setEmail("imterdal@gmail.com");
		employee.setPassword("qwertyuio");
		employee.setPhone("8978678978");
		this.mvc.perform(MockMvcRequestBuilders.post("/register").contentType(MediaType.APPLICATION_JSON)
				.content(jsonObject(employee))).andExpect(status().isOk()).andDo(print());
	}
}
