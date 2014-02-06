package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.data.db.PropImplDB.PropertyType;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.exceptions.DaoException;

public interface PropDAO {

	List<AbstractPersistentObj> getPropList(PropertyType prop) throws DaoException;

	AbstractPersistentObj getProp(PropertyType prop, long id) throws DaoException;

}
