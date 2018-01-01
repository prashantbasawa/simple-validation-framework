/**
 * 
 */
package com.pb.validation;

import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * @author Prashant Basawa
 *
 */
public class NumericTextValidation<T> extends Validation<T> {
	//
	private static Pattern numericPattern = Pattern.compile("\\d+");

	public NumericTextValidation(Function<T, String> getterFn, String fieldName) {
		super(domain -> numericPattern.matcher(getterFn.apply(domain)).matches(), "{0} should be numeric. [suppliedValue={1}]", d -> fieldName, getterFn);
	}
}
