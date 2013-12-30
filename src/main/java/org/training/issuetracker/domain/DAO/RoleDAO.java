package org.training.issuetracker.domain.DAO;

import java.util.List;
import java.util.Map;

import org.training.issuetracker.domain.Role;
import org.training.issuetracker.exceptions.DaoException;

public interface RoleDAO {
	/**
	 * Gets the roles.
	 * 
	 * @return list of Role
	 * @throws DaoException 
	 */
	
	//public List<Role> getRolesList() throws DaoException;
	public Map<Long, Role> getRolesMap() throws DaoException;
}
