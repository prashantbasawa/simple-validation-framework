/**
 * 
 */
package com.pb.validation;

import static com.pb.validation.ValidationUtils.isNotBlank;

import java.util.function.Function;

/**
 * @author Prashant Basawa
 *
 */
public class EmptyTextValidation<T> extends Validation<T> {

	public EmptyTextValidation(Function<T, String> getterFn, String fieldName) {
		super(domain -> isNotBlank(getterFn.apply(domain)), "{0} is required", d -> fieldName);
	}
}
