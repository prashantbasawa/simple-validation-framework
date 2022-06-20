package com.pb.validation;

import com.pb.usecase.Employee;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmptyTextValidationTest {

	@Test
	public void invalidcase_isValidCheck() {
		ValidationResult result = new EmptyTextValidation<>(Employee::getFirstName, "First name").test(new Employee());
		
		assertThat(result.isValid()).isFalse();
	}
	
	@Test
	public void invalidcase_reasonCheck() {
		ValidationResult result = new EmptyTextValidation<>(Employee::getFirstName, "First name").test(new Employee());
		
		assertThat(result.getReason()).isNotEmpty();
		assertThat(result.getReason().get()).isEqualTo("First name is required");
	}

	@Test
	public void validcase_isValidCheck() {
		Employee employee = new Employee();
		employee.setFirstName("Test");
		
		ValidationResult result = new EmptyTextValidation<>(Employee::getFirstName, "First name").test(employee);
		
		assertThat(result.isValid()).isTrue();
	}
	
	@Test
	public void validcase_reasonCheck() {
		Employee employee = new Employee();
		employee.setFirstName("Test");
		
		ValidationResult result = new EmptyTextValidation<>(Employee::getFirstName, "First name").test(employee);
		
		assertThat(result.getReason()).isEmpty();
	}
}
