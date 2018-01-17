package com.bridgeit.service;

import org.springframework.data.repository.CrudRepository;

import com.bridgeit.model.Employee;

public interface ServiceMethods extends CrudRepository<Employee, String> {
	public Employee findByEmail(String email);

	public Employee findEmployeeByPhone(String phone);

}
