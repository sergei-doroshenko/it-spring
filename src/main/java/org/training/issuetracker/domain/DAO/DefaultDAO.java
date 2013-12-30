package org.training.issuetracker.domain.DAO;

import java.util.Map;
import org.training.issuetracker.exceptions.DaoException;

public interface DefaultDAO {
	public <T> Map<Long, T> getResolutionMap() throws DaoException;
}
