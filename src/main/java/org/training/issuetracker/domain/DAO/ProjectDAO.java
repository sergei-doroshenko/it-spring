package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.Project;
import org.training.issuetracker.exceptions.DaoException;

public interface ProjectDAO {
	
	List<Project> getProjectsList() throws DaoException;

	Project getProject(long id)  throws DaoException;

	long insertProject(Project project) throws DaoException;

	void updateProject(Project project) throws DaoException;

	void deleteProject(long id) throws DaoException;

}
