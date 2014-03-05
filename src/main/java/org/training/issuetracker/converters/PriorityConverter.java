package org.training.issuetracker.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.training.issuetracker.domain.Priority;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.exceptions.DaoException;

public class PriorityConverter implements Converter<String, Priority> {
	
	@Autowired
	private PropDAO propDAO;
	
	@Override
	public Priority convert(String text) {
		long id = Long.parseLong(text);
		
		Priority priority = null;
		
		try {
			 priority = (Priority) propDAO.getProp(PropertyType.PRIORITY, id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return priority;
	}

}
