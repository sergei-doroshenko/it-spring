package org.training.issuetracker.domain.DAO;

import java.util.Map;

import org.training.issuetracker.domain.Type;
import org.training.issuetracker.exceptions.DaoException;

public interface TypeDAO {
	/**
	 * Gets the types.
	 * 
	 * @return Map of Type
	 * @throws DaoException 
	 */
	
	
	public Map<Long, Type> getTypesMap() throws DaoException;
}
