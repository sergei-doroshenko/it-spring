package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.data.db.StatusImplDB;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.exceptions.DaoException;

public interface StatusDAO {

	List<AbstractPersistentObj> getStatusesList(StatusImplDB.PropertyType prop) throws DaoException;
}
