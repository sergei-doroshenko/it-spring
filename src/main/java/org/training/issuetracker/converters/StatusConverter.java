package org.training.issuetracker.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.training.issuetracker.domain.Status;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.exceptions.DaoException;

public class StatusConverter implements Converter<String, Status> {
	
	@Autowired
	private PropDAO propDAO;
	
	@Override
	public Status convert(String text) {
		long id = Long.parseLong(text);
		
		Status status = null;
		
		try {
			 status = (Status) propDAO.getProp(PropertyType.STATUS, id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return status;
	}

}
