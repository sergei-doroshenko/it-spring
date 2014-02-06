package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.exceptions.DaoException;

public interface ProjectDAO {

	List<Project> getProjectsList() throws DaoException;

	Project getProject(long id)  throws DaoException;

	List<Build> getProjectBuilds (long id) throws DaoException;

	Build getBuild (long id) throws DaoException;

}
