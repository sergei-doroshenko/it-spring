package org.training.issuetracker.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.training.issuetracker.domain.User;

@Component
public class UserValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> cl) {
		
		return cl.equals(User.class);
	}

	@Override
	public void validate(Object obj, Errors err) {
				
		ValidationUtils.rejectIfEmpty(err, "firstName", "firstName.empty", "First name can't be empty!");
		ValidationUtils.rejectIfEmpty(err, "lastName", "lastName.empty", "Last name can't be empty!");
		ValidationUtils.rejectIfEmpty(err, "email", "email.empty", "Email name can't be empty!");
		ValidationUtils.rejectIfEmpty(err, "password", "password.empty", "Password name can't be empty!");
				
		User user = (User) obj;
		
		if (user.getFirstName().length() < 2) {
			err.rejectValue("firstName", "firstName.too.short", "First name must be between 1 and 20 characters long.");
		} else if (user.getLastName().length() < 3) {
			err.rejectValue("lastName", "lastName.too.short", "Last name can't be less than 3");
		} else if (user.getPassword().length() < 3) {
			err.rejectValue("password", "password.wrong", "Password can't be less than 3");
		}
		
		validateEmail(user.getEmail(), err);
	}
	
	private void validateEmail (String email, Errors err) {
		final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		
		if (email.length() < 6) {
			err.rejectValue("email", "email.wrong", "Email can't be less than 6 (e.g. h@1.fm)");
		} else if (!matcher.matches()) {
			err.rejectValue("email", "email.wrong", "Email start from number, letter or underscore charcter, contains @ and domain name (e.g. hello@gmail.com)");
		} 
	}

}
