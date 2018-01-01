/**
 * 
 */
package com.pb.validation;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Prashant Basawa
 *
 */
public class Validation<T> implements ValidationInterface<T> {
	private final Predicate<T> predicate;
	private final String failureReason;
	private final Function<T, ?>[] failureReasonArgFns;
	
	@SafeVarargs
	public Validation(final Predicate<T> predicate, final String failureReason, final Function<T, ?>...failureReasonArgFns) {
		this.predicate = predicate;
		this.failureReason = failureReason;
		this.failureReasonArgFns = failureReasonArgFns;
	}
	
	@Override
	public final ValidationResult test(T domain) {
		return predicate.test(domain) ? ValidationResult.valid(): ValidationResult.invalid(failureReason, failureReasonArgs(domain));
	}

	private Object[] failureReasonArgs(T domain) {
		if(failureReasonArgFns == null) { return null; }
		
		return Arrays.stream(this.failureReasonArgFns)
				.map(fn -> fn.apply(domain))
				.collect(toList())
				.toArray();
	}
}
