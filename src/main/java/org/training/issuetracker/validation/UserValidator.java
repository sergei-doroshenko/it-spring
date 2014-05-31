package org.training.issuetracker.validation;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.training.issuetracker.domain.User;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public boolean supports(Class<?> cl) {
		
		return cl.equals(User.class);
	}

	@Override
	public void validate(Object obj, Errors err) {
		Locale locale = LocaleContextHolder.getLocale();
		
		ValidationUtils.rejectIfEmpty(err, "firstName", "firstName.empty", messageSource.getMessage("user.firstName.empty", null, locale));
		ValidationUtils.rejectIfEmpty(err, "lastName", "lastName.empty", messageSource.getMessage("user.lastName.empty", null, locale));
		ValidationUtils.rejectIfEmpty(err, "email", "email.empty", messageSource.getMessage("user.email.empty", null, locale));
		ValidationUtils.rejectIfEmpty(err, "password", "password.empty", messageSource.getMessage("user.password.empty", null, locale));
		
		User user = (User) obj;
		
		if (user.getFirstName().length() < 2) {
			err.rejectValue("firstName", "firstName.too.short",  messageSource.getMessage("user.firstName.too.short", null, locale));
		} else if (user.getLastName().length() < 3) {
			err.rejectValue("lastName", "lastName.too.short", messageSource.getMessage("user.lastName.too.short", null, locale));
		} else if (user.getPassword().length() < 3) {
			err.rejectValue("password", "password.too.short", messageSource.getMessage("user.password.too.short", null, locale));
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
