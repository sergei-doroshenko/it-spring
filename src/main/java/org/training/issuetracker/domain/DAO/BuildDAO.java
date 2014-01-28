package org.training.issuetracker.domain.DAO;

import java.util.Map;

import org.training.issuetracker.domain.Build;
import org.training.issuetracker.exceptions.DaoException;

public interface BuildDAO {

	Map<Long, Build> getBuildMap() throws DaoException;

}
