package com.bridgeit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Employee;
import com.bridgeit.model.Response;
import com.bridgeit.service.ServiceImpl;

@RestController
public class UserController {

	@Autowired
	ServiceImpl service;

	@RequestMapping(value = "/getUserById/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> getUserById(@PathVariable int id) {
		Response response = new Response();
		Employee employee = new Employee();

		employee = service.getEmployeeById(id);

		if (employee != null) {
			response.setMessage("User Present In The DataBase....");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setMessage("User Not Present In The DataBase.......");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getAllEmployee", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> employee = service.getAllEmployee();
		Response response = new Response();
		if (employee != null) {
			return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
		} else {
			response.setMessage("Not Found Any Employee");
			return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		}
	}
}
