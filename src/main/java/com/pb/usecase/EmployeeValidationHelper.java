/**
 * 
 */
package com.pb.usecase;

import java.util.ArrayList;
import java.util.Collection;

import com.pb.validation.EmptyTextValidation;
import com.pb.validation.ValidationInterface;

/**
 * @author Prashant Basawa
 *
 */
public interface EmployeeValidationHelper {
	
	static Collection<ValidationInterface<Employee>> employeeValidations() {
		Collection<ValidationInterface<Employee>> validations = new ArrayList<>();
		
		validations.add(firstNameNotEmpty());
		validations.add(lastNameNotEmpty());
		
		return validations;
	}
	
	static ValidationInterface<Employee> firstNameNotEmpty() {
		return new EmptyTextValidation<>(Employee::getFirstName, "First name");
	}
	
	static ValidationInterface<Employee> lastNameNotEmpty() {
		return new EmptyTextValidation<>(Employee::getLastName, "Last name");
	}
}
