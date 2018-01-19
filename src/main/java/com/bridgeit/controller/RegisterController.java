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
import com.bridgeit.validation.Validation;

@RestController
public class RegisterController {

	@Autowired
	ServiceImpl service;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) {

		Response response = new Response();
		String message = Validation.isValid(employee);
		if (message != null) {
			response.setMessage(message);
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}

		boolean isSaved = service.save(employee);
		if (isSaved) {
			response.setMessage("Registration Sucessfull.........");
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response.setMessage("User Already Exsists.........");
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

}
