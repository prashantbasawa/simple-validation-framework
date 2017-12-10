/**
 * 
 */
package com.pb.validation;

import java.text.MessageFormat;
import java.util.Optional;

/**
 * @author Prashant Basawa
 *
 */
public interface ValidationResult {
	boolean isValid();
	Optional<String> getReason();
	
	static ValidationResult valid() {
		return SingletonHelper.valid;
	}
	
	static ValidationResult invalid(String reason, Object...reasonArgs) {
		return new Invalid(reason, reasonArgs);
	}
	
	public static class SingletonHelper {
		private static final ValidationResult valid = new ValidationResult() {
			@Override
			public boolean isValid() { return true; }
			
			@Override
			public Optional<String> getReason() { return Optional.empty(); }

			@Override
			public String toString() { return "Valid[]"; }
		};
	}
	
	public static class Invalid implements ValidationResult {
		private final String reason;
		
		private Invalid(final String reason, Object...reasonArgs) {
			if(reason == null) { throw new IllegalArgumentException("Reason is required"); }
			
			this.reason = MessageFormat.format(reason, reasonArgs);
		}

		@Override
		public boolean isValid() {
			return false;
		}

		@Override
		public Optional<String> getReason() {
			return Optional.of(reason);
		}
		
		@Override
		public String toString() { 
			return "Invalid[reason=" + reason + "]"; 
		}
	}
}
