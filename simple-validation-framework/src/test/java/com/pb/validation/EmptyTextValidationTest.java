package com.pb.validation;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

import com.pb.employee.Employee;
import com.pb.validation.EmptyTextValidation;
import com.pb.validation.ValidationResult;

public class EmptyTextValidationTest {

	@Test
	public void invalidcase_isValidCheck() {
		ValidationResult result = new EmptyTextValidation<>(Employee::getFirstName, "First name").test(new Employee());
		
		assertThat(result.isValid(), is(false));
	}
	
	@Test
	public void invalidcase_reasonCheck() {
		ValidationResult result = new EmptyTextValidation<>(Employee::getFirstName, "First name").test(new Employee());
		
		assertThat(result.getReason(), not(Optional.empty()));
		assertThat(result.getReason().get(), is("First name is required"));
	}

	@Test
	public void validcase_isValidCheck() {
		Employee employee = new Employee();
		employee.setFirstName("Test");
		
		ValidationResult result = new EmptyTextValidation<>(Employee::getFirstName, "First name").test(employee);
		
		assertThat(result.isValid(), is(true));
	}
	
	@Test
	public void validcase_reasonCheck() {
		Employee employee = new Employee();
		employee.setFirstName("Test");
		
		ValidationResult result = new EmptyTextValidation<>(Employee::getFirstName, "First name").test(employee);
		
		assertThat(result.getReason(), is(Optional.empty()));
	}
}
