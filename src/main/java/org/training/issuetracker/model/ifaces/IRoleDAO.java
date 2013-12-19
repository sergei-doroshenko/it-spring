package org.training.issuetracker.model.ifaces;

import java.util.List;

import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.model.beans.Role;

public interface IRoleDAO {
	/**
	 * Gets the roles.
	 * 
	 * @return list of Role
	 * @throws DaoException 
	 */
	
	public List<Role> getRoles() throws DaoException;
}
