package org.training.issuetracker.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;

public class UserConverter implements Converter<String, User> {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User convert(String text) {
		long id = Long.parseLong(text);
		
		User user = null;
		
		try {
			 user = (User) userDAO.getUser(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return user;
	}

}
