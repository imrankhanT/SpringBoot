package com.bridgeit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Employee;
import com.bridgeit.model.Response;
import com.bridgeit.service.ServiceImpl;

@RestController
public class LoginController {

	@Autowired
	ServiceImpl service;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> loginEmployee(@RequestBody Employee employee) {
		Response response = new Response();
		String empName = service.login(employee);
		if (empName != null) {
			response.setMessage("Login Sucessfully.......");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setMessage("Invalid Emial_Id Or password......");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
