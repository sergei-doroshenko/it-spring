package org.training.issuetracker.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EmailConstraintValidator implements ConstraintValidator<CheckEmail, String> {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;
	
	public EmailConstraintValidator () {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	public void initialize(CheckEmail arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		
		System.out.println("Email validator work ---------------------------------------------------------------");
		
		boolean result = true;
		matcher = pattern.matcher(email);
		if (email.isEmpty() || email == null || !matcher.matches()) {
			
			result = false;
		}
		return result;
	}
	
//	@Override
//	public boolean supports(Class<?> cl) {
//		
//		return cl.equals(String.class);
//	}
//
//	@Override
//	public void validate(Object obj, Errors err) {
//				
//		ValidationUtils.rejectIfEmpty(err, "email", "email.empty", "Email name can't be empty!");
//		
//		String email = (String) obj;
//		
//		matcher = pattern.matcher(email);
//		
//		if (email.length() < 6) {
//			err.rejectValue("email", "email.wrong", "Email can't be less than 6 (e.g. h@1.fm)");
//			throw new IllegalArgumentException("Email can't be less than 6 (e.g. h@1.fm)----------------------------------------------------------------------------");
//		} else if (!matcher.matches()) {
//			err.rejectValue("email", "email.wrong", "Email start from number, letter or underscore charcter, contains @ and domain name (e.g. hello@gmail.com)");
//		} 
//		
//		System.out.println("Email validation errors ================ ----------------------------" + err.getAllErrors());
//	}
	
}
