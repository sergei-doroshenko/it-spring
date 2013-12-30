package org.training.issuetracker.domain.DAO;

import java.util.Map;

import org.training.issuetracker.domain.Resolution;
import org.training.issuetracker.exceptions.DaoException;

public interface ResolutionDAO {
	public Map<Long, Resolution> getResolutionMap() throws DaoException;
}
