/**
 * 
 */
package com.pb.validation;

/**
 * @author Prashant Basawa
 *
 */
@FunctionalInterface
public interface ValidationInterface<T> {
	ValidationResult test(T domain);
	
	default ValidationInterface<T> and(ValidationInterface<T> other) {
		return domain -> {
			ValidationResult result = this.test(domain);
			return result.isValid() ? other.test(domain) : result;
		};
	}
	
	default ValidationInterface<T> or(ValidationInterface<T> other) {
		return domain -> {
			ValidationResult result = this.test(domain);
			return result.isValid() ? result : other.test(domain);
		};
	}
}
