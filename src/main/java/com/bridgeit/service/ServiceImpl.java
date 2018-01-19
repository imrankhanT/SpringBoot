package com.bridgeit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.model.Employee;

@Service
public class ServiceImpl {

	@Autowired
	private ServiceMethods serviceMethods;

	public boolean save(Employee employee) {
		Employee emp1 = getEmployeeByEmail(employee.getEmail());
		Employee emp2 = getEmployeeByPhone(employee.getPhone());
		if (emp1 != null || emp2 != null) {
			return false;
		} else {
			serviceMethods.save(employee);
			return true;
		}
	}

	public String login(Employee employee) {
		Employee emp1 = getEmployeeByEmail(employee.getEmail());

		String isChickPasss = emp1.getPassword();

		if (emp1 != null && isChickPasss.equals(employee.getPassword())) {
			return emp1.getEmpName();
		} else {
			return null;
		}
	}

	public Employee getEmployeeByEmail(String email) {
		Employee emp = serviceMethods.findByEmail(email);
		return emp;
	}

	public Employee getEmployeeByPhone(String phone) {
		Employee emp = serviceMethods.findEmployeeByPhone(phone);
		return emp;
	}

	public Employee getEmployeeById(int id) {
		Employee employee = serviceMethods.findEmployeeById(id);
		return employee;
	}

	public List<Employee> getAllEmployee() {
		return (List<Employee>) serviceMethods.findAll();
	}
}
