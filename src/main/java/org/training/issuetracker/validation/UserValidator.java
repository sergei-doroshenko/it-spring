package org.training.issuetracker.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.training.issuetracker.domain.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> cl) {
		
		return User.class.equals(cl);
	}

	@Override
	public void validate(Object obj, Errors err) {
		
		ValidationUtils.rejectIfEmpty(err, "firstName", "firstName.empty");
		ValidationUtils.rejectIfEmpty(err, "lastName", "lastName.empty");
		ValidationUtils.rejectIfEmpty(err, "email", "email.empty");
		ValidationUtils.rejectIfEmpty(err, "password", "password.empty");
		
		User user = (User) obj;
		
		if (user.getFirstName().length() < 3) {
			err.rejectValue("firstName", "too short");
		} else if (user.getLastName().length() < 3) {
			err.rejectValue("lastName", "can't be less than 3");
		} else if (user.getEmail().length() < 5) {
			err.rejectValue("email", "can't be less than 5");
		} else if (user.getPassword().length() < 3) {
			err.rejectValue("password", "can't be less than 3");
		}
	}

}
