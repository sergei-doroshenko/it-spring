package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.Build;
import org.training.issuetracker.exceptions.DaoException;

public interface BuildDAO {

	List<Build> getBuildsList() throws DaoException;
	
	List<Build> getProjectBuilds (long id) throws DaoException;

	Build getBuild (long id) throws DaoException;

	long insertBuild (Build build) throws DaoException;

	void updateBuild (Build build) throws DaoException;

	void deleteBuild (long id) throws DaoException;

}
