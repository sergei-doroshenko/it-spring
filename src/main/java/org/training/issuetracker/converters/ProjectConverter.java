package org.training.issuetracker.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.exceptions.DaoException;

public class ProjectConverter implements Converter<String, Project> {
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@Override
	public Project convert(String text) {
		long id = Long.parseLong(text);
		
		Project project = null;
		
		try {
			 project = (Project) projectDAO.getProject(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return project;
	}

}
