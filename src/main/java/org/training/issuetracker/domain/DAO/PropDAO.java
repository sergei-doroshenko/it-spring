package org.training.issuetracker.domain.DAO;

import java.util.List;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.exceptions.DaoException;

public interface PropDAO {

	List<AbstractPersistentObj> getPropList(PropertyType prop) throws DaoException;

	AbstractPersistentObj getProp(PropertyType prop, long id) throws DaoException;
	
	long insertProp (PropertyType prop, AbstractPersistentObj propObject) throws DaoException;
	
	void updateProp (PropertyType prop, AbstractPersistentObj propObject) throws DaoException;
	
	void deleteProp (PropertyType prop, long id) throws DaoException;
	
	public enum PropertyType {
		STATUS, RESOLUTION, PRIORITY, TYPE, ROLE
	}
}
