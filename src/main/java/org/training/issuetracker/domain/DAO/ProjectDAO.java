package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.exceptions.DaoException;

public interface ProjectDAO {

	List<Project> getProjectsFormList() throws DaoException;

	Project getProject(long id)  throws DaoException;

	long insertProject(Project project) throws DaoException;

	long updateProject(Project project) throws DaoException;

	long deleteProject(long id) throws DaoException;

	List<Build> getProjectBuilds (long id) throws DaoException;

	Build getBuild (long id) throws DaoException;

	long insertBuild (Build build) throws DaoException;

	long updateBuild (Build build) throws DaoException;

	long deleteBuild (long id) throws DaoException;

	List<Project> getProjectsList() throws DaoException;

	List<Build> getBuildsList() throws DaoException;

}
