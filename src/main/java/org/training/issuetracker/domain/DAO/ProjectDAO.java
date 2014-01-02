package org.training.issuetracker.domain.DAO;

import java.util.Map;

import org.training.issuetracker.domain.Project;
import org.training.issuetracker.exceptions.DaoException;

public interface ProjectDAO {
	
	Map<Long, Project> getProjectsMap() throws DaoException;
}
