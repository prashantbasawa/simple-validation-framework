/**
 * 
 */
package com.pb.usecase;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;

import com.pb.validation.EmptyTextValidation;
import com.pb.validation.NumericTextValidation;
import com.pb.validation.TextLengthValidation;
import com.pb.validation.ValidationInterface;
import com.pb.validation.ValidationResult;

/**
 * @author Prashant Basawa
 *
 */
public interface EmployeeValidationHelper {
	
	static Collection<ValidationResult> validate(Employee employee) {
		return employeeValidations().stream().map(v -> v.test(employee)).collect(toList());
	}
	
	static Collection<ValidationInterface<Employee>> employeeValidations() {
		Collection<ValidationInterface<Employee>> validations = new ArrayList<>();
		
		validations.add(firstNameNotEmpty());
		validations.add(lastNameNotEmpty());
		validations.add(zipCodeNotEmpty().and(zipCode5CharsLong()).and(zipCodeNumeric()));
		
		return validations;
	}
	
	static ValidationInterface<Employee> firstNameNotEmpty() {
		return new EmptyTextValidation<>(Employee::getFirstName, "First name");
	}
	
	static ValidationInterface<Employee> lastNameNotEmpty() {
		return new EmptyTextValidation<>(Employee::getLastName, "Last name");
	}
	
	static ValidationInterface<Employee> zipCodeNotEmpty() {
		return new EmptyTextValidation<>(Employee::getZipCode, "Zip Code");
	}
	
	static ValidationInterface<Employee> zipCodeNumeric() {
		return new NumericTextValidation<>(Employee::getZipCode, "Zip Code");
	}
	
	static ValidationInterface<Employee> zipCode5CharsLong() {
		return new TextLengthValidation<Employee>(Employee::getZipCode, "Zip Code", 5);
	}
}
