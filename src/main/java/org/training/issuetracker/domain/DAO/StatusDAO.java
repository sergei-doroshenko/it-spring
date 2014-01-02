package org.training.issuetracker.domain.DAO;

import java.util.Map;

import org.training.issuetracker.domain.Status;
import org.training.issuetracker.exceptions.DaoException;

public interface StatusDAO {
	
	Map<Long, Status> getStatusesMap() throws DaoException;
	

}
