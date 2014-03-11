package org.training.issuetracker.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.exceptions.DaoException;

public class RoleConverter implements Converter<String, Role> {
	
	@Autowired
	private PropDAO propDAO;
	
	@Override
	public Role convert(String text) {
		long id = Long.parseLong(text);
		
		Role type = null;
		
		try {
			 type = (Role) propDAO.getProp(PropertyType.ROLE, id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return type;
	}

}
