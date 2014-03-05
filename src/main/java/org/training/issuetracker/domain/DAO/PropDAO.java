package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.SearchFilterParams;

public interface PropDAO {
	
	int getPropRecordsCount(PropertyType propType) throws DaoException;

	List<AbstractPersistentObj> getPropList(PropertyType prop, SearchFilterParams params) throws DaoException;
	
	List<AbstractPersistentObj> getPropList(PropertyType prop) throws DaoException;
	
	AbstractPersistentObj getProp(PropertyType prop, long id) throws DaoException;
	
	long insertProp (PropertyType prop, AbstractPersistentObj propObject) throws DaoException;
	
	void updateProp (PropertyType prop, AbstractPersistentObj propObject) throws DaoException;
	
	void deleteProp (PropertyType prop, long id) throws DaoException;
	
}
