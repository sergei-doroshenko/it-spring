package org.training.issuetracker.data.hiber;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.exceptions.DaoException;

public class PropImplHiber implements PropDAO {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	
	
	@Override
	public int getPropRecordsCount(PropertyType prop) throws DaoException {
		String entityName = prop.getEntitiName();
		logger.debug("Records count ================================================= " + DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from " + entityName)));
		return DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from " + entityName));
	}

	@Override
	public List getPropList(PropertyType prop, int page, int rows, String sidx, String sord) throws DaoException {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		
		int firstResult = (page -1) * rows;
		
		List result = hibernateTemplate.findByCriteria(criteria, firstResult, rows);
		return result;
	}

	@Override
	public AbstractPersistentObj getProp(PropertyType prop, long id)
			throws DaoException {
		
		return (AbstractPersistentObj) hibernateTemplate.get(prop.getEntitiName(), id);
	}

	@Override
	public long insertProp(PropertyType prop, AbstractPersistentObj propObject)
			throws DaoException {
		
		return (Long) hibernateTemplate.save(propObject);
	}

	@Override
	public void updateProp(PropertyType prop, AbstractPersistentObj propObject)
			throws DaoException {
		
		hibernateTemplate.update(propObject);
		
	}

	@Override
	public void deleteProp(PropertyType prop, long id) throws DaoException {
		
		hibernateTemplate.delete(hibernateTemplate.get(prop.getEntitiName(), id));
	}

}
