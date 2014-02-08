package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.data.db.PropImplDB.PropertyType;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.exceptions.DaoException;

public interface PropDAO {

	List<AbstractPersistentObj> getPropList(PropertyType prop) throws DaoException;

	AbstractPersistentObj getProp(PropertyType prop, long id) throws DaoException;
	
	long insertProp (PropertyType prop, AbstractPersistentObj propObject) throws DaoException;
	
	long updateProp (PropertyType prop, AbstractPersistentObj propObject) throws DaoException;
	
	long deleteProp (PropertyType prop, long id) throws DaoException;
 
}
