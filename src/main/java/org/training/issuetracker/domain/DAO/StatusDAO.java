package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.data.db.PropImplDB;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.exceptions.DaoException;

public interface StatusDAO {

	List<AbstractPersistentObj> getPropList(PropImplDB.PropertyType prop) throws DaoException;
}
