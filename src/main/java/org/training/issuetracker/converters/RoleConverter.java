package org.training.issuetracker.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.exceptions.DaoException;

public class RoleConverter implements Converter<String, Role> {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private PropDAO propDAO;
	
	@Override
	public Role convert(String text) {
		long id = Long.parseLong(text);
		
		Role role = null;
		
		try {
			 role = (Role) propDAO.getProp(PropertyType.ROLE, id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		logger.debug("Editor return Role -----------------------------------------------------------------------------------------------------------------" + role);
		
		return role;
	}

}
