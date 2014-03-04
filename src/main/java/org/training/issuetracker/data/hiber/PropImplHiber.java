package org.training.issuetracker.data.hiber;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.SearchFilterParams;

public class PropImplHiber implements PropDAO {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public int getPropRecordsCount(PropertyType prop) throws DaoException {
		String entityName = prop.getEntityName();
		return DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from " + entityName));
	}

	@Override
	public List<AbstractPersistentObj> getPropList(PropertyType prop, SearchFilterParams params) throws DaoException {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(prop.getPropClass());
		int page = params.getPage();
		int rows = params.getRows();
		int firstResult = (page - 1) * rows;
		String sord = params.getSord();
		
		if (sord.equals(SearchFilterParams.ASC)) {
			criteria.addOrder(Order.asc(params.getSidx()));
		} else {
			criteria.addOrder(Order.desc(params.getSidx()));
		}
		
		List result = hibernateTemplate.findByCriteria(criteria, firstResult, rows);

		return Collections.checkedList(result, AbstractPersistentObj.class);
	}
	
	@Override
	public List<AbstractPersistentObj> getProjectsList(PropertyType prop) throws DaoException {
		List result = hibernateTemplate.find("from " + prop.getEntityName());
		return Collections.checkedList(result, AbstractPersistentObj.class);
	}
	
	@Override
	public AbstractPersistentObj getProp(PropertyType prop, long id)
			throws DaoException {
		
		return (AbstractPersistentObj) hibernateTemplate.get(prop.getEntityName(), id);
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
		
		hibernateTemplate.delete(hibernateTemplate.get(prop.getEntityName(), id));
	}

}
