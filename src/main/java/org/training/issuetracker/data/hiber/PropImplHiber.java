package org.training.issuetracker.data.hiber;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.training.issuetracker.data.db.PropImplDB.PropertyType;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.exceptions.DaoException;

public class PropImplHiber implements PropDAO {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<AbstractPersistentObj> getPropList(PropertyType prop)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractPersistentObj getProp(PropertyType prop, long id)
			throws DaoException {
		
		switch (prop) {
			case STATUS : {
				
				break;
			}
			case RESOLUTION : {
				
				break;
			}
			case PRIORITY : {
				
				break;
			}
			case TYPE : {
				
				break;
			}
			case ROLE : {
				return hibernateTemplate.get(Role.class, id);
			}
			default : {
				break;
			}
		}
		
		return null;
	}

	@Override
	public long insertProp(PropertyType prop, AbstractPersistentObj propObject)
			throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long updateProp(PropertyType prop, AbstractPersistentObj propObject)
			throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteProp(PropertyType prop, long id) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

}
