/**
 * 
 */
package com.pb.validation;

/**
 * @author Prashant Basawa
 *
 */
public interface ValidationUtils {
	
	static boolean isBlank(String value) {
		return value == null || value.trim().length() == 0;
	}
	
	static boolean isNotBlank(String value) {
		return !isBlank(value);
	}
	
	static int length(String value) {
		return value == null ? 0 : value.length();
	}
}
