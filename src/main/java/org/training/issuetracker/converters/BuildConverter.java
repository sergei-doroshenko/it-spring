package org.training.issuetracker.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.DAO.BuildDAO;
import org.training.issuetracker.exceptions.DaoException;

public class BuildConverter implements Converter<String, Build> {
	
	@Autowired
	private BuildDAO buildDAO;
	
	@Override
	public Build convert(String text) {
		long id = Long.parseLong(text);
		
		Build build = null;
		
		try {
			 build = (Build) buildDAO.getBuild(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return build;
	}

}
