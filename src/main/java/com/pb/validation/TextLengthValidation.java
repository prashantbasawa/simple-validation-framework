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
public class TextLengthValidation<T> extends Validation<T> {

	public TextLengthValidation(Function<T, String> getterFn, String fieldName, int requiredLength) {
		super(domain -> length(getterFn.apply(domain)) == requiredLength, "{0} should be {1} characters long", d -> fieldName, d -> requiredLength);
	}
}
