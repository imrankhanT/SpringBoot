package com.bridgeit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bridgeit.controller.HomeController;
import com.bridgeit.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegitsrationSpringBootApplicationTests {

	@Autowired
	HomeController homeController;

	@Test
	public void contextLoads() {
		Employee employee = new Employee();
		employee.setEmail("imterdal@gmail.com");
		employee.setPassword("qwertyuio");
		System.out.println(homeController.loginEmployee(employee));
		assert (homeController.loginEmployee(employee)) != null;
	}

	@Test
	public void register() {
		Employee employee = new Employee();
		employee.setEmail("adam@gmail.com");
		employee.setEmpName("Adam Voges");
		employee.setPassword("adam123456");
		employee.setPhone("8796096879");
		assert (homeController.registerEmployee(employee)) != null;
	}

}
