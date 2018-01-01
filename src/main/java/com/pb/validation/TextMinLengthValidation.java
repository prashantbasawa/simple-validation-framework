/**
 * 
 */
package com.pb.validation;

import static com.pb.validation.ValidationUtils.length;

import java.util.function.Function;

/**
 * @author Prashant Basawa
 *
 */
public class TextMinLengthValidation<T> extends Validation<T> {

	public TextMinLengthValidation(Function<T, String> getterFn, String fieldName, int minLength) {
		super(domain -> length(getterFn.apply(domain)) >= minLength, "{0} should have minimum of {1} characters", d -> fieldName, d -> minLength);
	}
}
