package com.bridgeit.validation;

import com.bridgeit.model.Employee;

public class Validation {
	public static String isValid(Employee user) {
		String phoneNumRegx = "^[0-9]{10}$";
		String nameRegx = "^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$";
		String emailRegx = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if (!(user.getEmpName()).matches(nameRegx)) {
			return "Name To Short.......";
		} else

		if (!user.getPhone().matches(phoneNumRegx)) {
			return "Phone Number Should Be 10 Digit......";
		} else

		if (!user.getEmail().matches(emailRegx)) {
			return "Invalid Email";
		} else if (user.getPassword().length() < 7 || user.getPassword().length() > 16) {
			return "Please! Enter The Password between 7 to 16 charcters......";
		} else {
			return null;
		}
	}
}
