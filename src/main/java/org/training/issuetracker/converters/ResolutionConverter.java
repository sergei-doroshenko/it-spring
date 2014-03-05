package org.training.issuetracker.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.training.issuetracker.domain.Resolution;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.exceptions.DaoException;

public class ResolutionConverter implements Converter<String, Resolution> {
	
	@Autowired
	private PropDAO propDAO;
	
	@Override
	public Resolution convert(String text) {
		long id = Long.parseLong(text);
		
		Resolution resolution = null;
		
		try {
			 resolution = (Resolution) propDAO.getProp(PropertyType.RESOLUTION, id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return resolution;
	}

}
