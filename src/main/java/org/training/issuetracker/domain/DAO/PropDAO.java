package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.exceptions.DaoException;

public interface PropDAO {

	AbstractPersistentObj getProp(PropertyType prop, long id) throws DaoException;
	
	long insertProp (PropertyType prop, AbstractPersistentObj propObject) throws DaoException;
	
	void updateProp (PropertyType prop, AbstractPersistentObj propObject) throws DaoException;
	
	void deleteProp (PropertyType prop, long id) throws DaoException;

	List<AbstractPersistentObj> getPropList(PropertyType propType, int page, int rows, String sidx, String sord) throws DaoException;

	int getPropRecordsCount(PropertyType propType) throws DaoException;
	
}
