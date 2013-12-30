package org.training.issuetracker.domain.DAO;

import java.util.Map;

import org.training.issuetracker.domain.Priority;
import org.training.issuetracker.exceptions.DaoException;

public interface PriorityDAO {
	/**
	 * Gets the Priorities.
	 * 
	 * @return Map of Priority
	 * @throws DaoException 
	 */
	
	
	public Map<Long, Priority> getPriorityMap() throws DaoException;
}
