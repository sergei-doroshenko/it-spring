package org.training.issuetracker.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;

public class UserConverter implements Converter<String, User> {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private UserDAO userDAO;
	
	@SuppressWarnings("null")
	@Override
	public User convert(String text) {
		
		User user = null;
		
		if (text != null || !text.isEmpty()) {
			long id = Long.parseLong(text);
			
			try {
				 user = (User) userDAO.getUser(id);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}

}
