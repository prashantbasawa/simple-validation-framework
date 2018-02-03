/**
 * 
 */
package com.pb.usecase;

import java.util.Collection;

import com.pb.validation.ValidationResult;

/**
 * @author Prashant Basawa
 *
 */
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee employee = new Employee();
		
		Collection<ValidationResult> errors = EmployeeValidationHelper.validate(employee);
		errors.stream().forEach(e -> System.out.println(e.getReason().get()));
	}
}
