/**
 * 
 */
package com.pb.validation;

import java.util.function.Predicate;

/**
 * @author Prashant Basawa
 *
 */
public class IfConditionThenValidation<T> implements ValidationInterface<T> {
	//
	private final Predicate<T> condition;
	private final ValidationInterface<T> validation;
	
	public IfConditionThenValidation(Predicate<T> condition, ValidationInterface<T> validation) {
		this.condition = condition;
		this.validation = validation;
	}
	
	@Override
	public ValidationResult test(T domain) {
		return condition.test(domain) ? validation.test(domain) : ValidationResult.valid();
	}
}
