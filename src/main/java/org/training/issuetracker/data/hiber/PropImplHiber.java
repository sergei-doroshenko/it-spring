package org.training.issuetracker.data.hiber;

import java.awt.Window.Type;
import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.training.issuetracker.data.db.PropImplDB.PropertyType;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.Priority;
import org.training.issuetracker.domain.Resolution;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.exceptions.DaoException;

public class PropImplHiber implements PropDAO {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	private Class<?> getPropClass(PropertyType prop) {
		Class<?> cl = null;
		switch (prop) {
			case STATUS : {
				cl = Status.class;
				break;
			}
			case RESOLUTION : {
				cl = Resolution.class;
				break;
			}
			case PRIORITY : {
				cl = Priority.class;
				break;
			}
			case TYPE : {
				cl = Type.class;
				break;
			}
			case ROLE : {
				cl = Role.class;
			}
			default : {
				break;
			}
		}
		
		return cl;
	}
	
	private String getPropEntityName(PropertyType prop) {
		String entityName = null;
		switch (prop) {
			case STATUS : {
				entityName = Status.class.getCanonicalName();
				break;
			}
			case RESOLUTION : {
				entityName = Resolution.class.getCanonicalName();
				break;
			}
			case PRIORITY : {
				entityName = Priority.class.getCanonicalName();
				break;
			}
			case TYPE : {
				entityName = Type.class.getCanonicalName();
				break;
			}
			case ROLE : {
				entityName = Role.class.getCanonicalName();
			}
			default : {
				break;
			}
		}
		
		return entityName;
	}
	
	@Override
	public List getPropList(PropertyType prop)
			throws DaoException {
		
		String className = getPropClass(prop).getSimpleName();
		return hibernateTemplate.find("from " + className);
	}
	
	@Override
	public AbstractPersistentObj getProp(PropertyType prop, long id)
			throws DaoException {
		
		String entityName = getPropClass(prop).getCanonicalName();
		return (AbstractPersistentObj) hibernateTemplate.get(entityName, id);
	}

	@Override
	public long insertProp(PropertyType prop, AbstractPersistentObj propObject)
			throws DaoException {
		
		return (Long) hibernateTemplate.save(propObject);
	}

	@Override
	public long updateProp(PropertyType prop, AbstractPersistentObj propObject)
			throws DaoException {
		
		hibernateTemplate.update(propObject);
		return 0;
	}

	@Override
	public long deleteProp(PropertyType prop, long id) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

}
