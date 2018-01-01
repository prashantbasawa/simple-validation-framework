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
public class TextMaxLengthValidation<T> extends Validation<T> {

	public TextMaxLengthValidation(Function<T, String> getterFn, String fieldName, int maxLength) {
		super(domain -> length(getterFn.apply(domain)) <= maxLength, "{0} can have maximum of {1} characters", d -> fieldName, d -> maxLength);
	}
}
