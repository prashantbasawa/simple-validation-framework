# Simple Domain Validation Framework Using Java 8
Simple ? Yes, it is. There are cases, you will want to have a simple validation framework to validate your java objects and where bringing a third party library is a overkill. You can use this design in those situations.

#### What is a validation ?
>A validation is really a predicate/condition that is when tested on a object, if it succeeds, then object is considered to have passed, otherwise, it is considered failed. If it failed, then we need to know why it failed, so that we can correct it.

To start with, let's define an interface, which represents a validation contract.

```Java
@FunctionalInterface
public interface ValidationInterface<T> {
    ValidationResult test(T domain);
}
```
As you can see, it has a generic type, which is the type of object, it is going to validate. It has a method ```test()``` to test the object. The interface is defined as a ```FunctionalInterface```, so we can make use of lambdas. In Java 8, interfaces can have default method implementations, so let's create two methods which we can use to combine the validations to create logical OR'ing & AND'ing capability.

```Java
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
```
The result of the test will be either valid or invalid. If it is invalid then we need the reason for the failed test. So, let define the ```ValidationResult``` as an interface with these details.
```Java
public interface ValidationResult {
	boolean isValid();
	Optional<String> getReason();
}
```
As you can see, ```ValidationResult``` has two methods, ```isValid()``` to indicate if validation passed and ```getReason()``` method to give the reason. The ```getReason()``` method, returns a ```Optional``` value, since, there will be no reason if the validation succeeds.

Now, let's add two implementations for ```ValidationResult``` to represent validness and invalidness.
```Java
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
```
Since, validness doesn't have any other state apart from being valid, it can be represented by a singleton class. The ```SingletonHelper``` is helping in creating this singleton instance for us. The class ```Invalid``` represents invalidness and has reason attribute. The constructor of class  ```Invalid``` also takes an object array to present the placeholders in the reason message to facilitate dynamic messages. Finally, we have two factory methods ```valid()``` and ```invalid()``` to return the instances of the implementation classes.
