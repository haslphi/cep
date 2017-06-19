package at.jku.ce.cep.common;

import com.vaadin.data.Validator;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;

public class ValidatorFactory {
	
	public static final Validator createStringLenghtValidator(String fieldName, int minLength, int maxLength, boolean allowNull) {
		String message = String.format("The %s must be %d-%d letters.", fieldName, minLength, maxLength);
		return new StringLengthValidator(message, minLength, maxLength, allowNull);
	}
	
	public static final Validator createEmailValidator() {
		return new EmailValidator("The email address is syntactically  invalid.");
	}
	
	public static final Validator createIntegerRangeValidator(String fieldName, int minValue, int maxValue) {
		String message = String.format("The %s must be an integer between %d-%d.", fieldName, minValue, maxValue);
		return new IntegerRangeValidator(message, minValue, maxValue);
	}
	
	public static final Validator createDoubleRangeValidator(String fieldName, double minValue, double maxValue) {
		String message = String.format("The %1s must be an decimal value between %2$,.2f-%3$,.2f.", fieldName, minValue, maxValue);
		return new DoubleRangeValidator(message, minValue, maxValue);
	}
}
