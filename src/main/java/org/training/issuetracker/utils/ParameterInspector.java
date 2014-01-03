package org.training.issuetracker.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.exceptions.ValidationException;

public class ParameterInspector {
	public static final String NAME_PATTERN = "[a-zA-Z0-9]{4,6}";
    public static final String PASSWORD_PATTERN = "[a-zA-Z0-9]{3,6}";
    public static final String EMAIL_PATTERN = "^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\\.([a-zA-Z])+";
	
	public ParameterInspector() {}
	
	public static boolean checkName(String name) throws ValidationException{
		Pattern pattern = Pattern.compile(NAME_PATTERN);
		
		if(name == null){
			throw new ValidationException("login empty!");//Constants.ERROR_LOGIN
		} else {
			Matcher matcher = pattern.matcher(name);
			if(!matcher.matches()){
				throw new ValidationException(Constants.ERROR_LOGIN);
			}
			return true;
		}
	}
	
	public static boolean checkPassword(String pass, String confpass) throws ValidationException{
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(pass);
		
		if(!matcher.matches() || !pass.equals(confpass)){
			throw new ValidationException(Constants.ERROR_PASSWORD);
		}
		
		return true;
	}
	
	public static boolean checkPassword(String pass) throws ValidationException{
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(pass);
		
		if(!matcher.matches()){
			throw new ValidationException(Constants.ERROR_PASSWORD);
		}
		
		return true;
	}
	
	
	public static boolean checkEmail(String name) throws ValidationException{
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(name);
		
		if(!matcher.matches()){
			throw new ValidationException(Constants.ERROR_EMAIL);
		}
		
		return true;
	}
	
	public static User checkUser(String firstName, String lastName, String mail,  
			String pass, String confpass) throws ValidationException{
		
		User user = new User();
		
		if(checkName(firstName) && checkName(lastName) && checkPassword(pass, confpass) 
				&& checkEmail(mail)){
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(mail);
			user.setPassword(pass);
		}
		
		return user;
			 
	}
}
