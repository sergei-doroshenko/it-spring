package org.training.issuetracker.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;

public class UserConstraintValidator implements ConstraintValidator<CheckUser, User>{
	
	@Autowired
	private PropDAO propDAO;
	
	@Override
	public void initialize(CheckUser arg) {
		
	}

	@Override
	public boolean isValid(User user, ConstraintValidatorContext context) {
		boolean result = true;
		
//		Role role = (Role) propDAO.getProp(PropertyType.ROLE, id);
		
		System.out.println("Validator works ----------------------------------------------------------");
		if (user.getFirstName().length() < 2 || user.getLastName().length() < 3
				|| user.getPassword().length() < 3) {
			
			result = false;
		}
		
		return result;
	}

}
