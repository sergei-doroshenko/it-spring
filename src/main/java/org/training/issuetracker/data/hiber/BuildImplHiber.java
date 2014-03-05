package org.training.issuetracker.data.hiber;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.BuildDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.SearchFilterParams;

public class BuildImplHiber implements BuildDAO {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List getBuildsList() throws DaoException {
		return hibernateTemplate.find("from Build");
	}
	
	@Override
	public List<Build> getBuildsList(SearchFilterParams params)
			throws DaoException {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Build.class);
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

		return Collections.checkedList(result, Build.class);
	}
	
	@Override
	public List getProjectBuilds(long id) throws DaoException {
		List result = hibernateTemplate.find("from Build where project.id=?", id); 
		return Collections.checkedList(result, Build.class);
	}

	@Override
	public Build getBuild(long id) throws DaoException {
		return (Build) hibernateTemplate.get(Build.class, id);
	}

	@Override
	public long insertBuild(Build build) throws DaoException {
		return (Long) hibernateTemplate.save(build);
	}

	@Override
	public void updateBuild(Build build) throws DaoException {
		hibernateTemplate.update(build);
	}

	@Override
	public void deleteBuild(long id) throws DaoException {
		hibernateTemplate.delete(hibernateTemplate.get(Build.class, id));
	}

	@Override
	public int getBuildsRecordsCount() throws DaoException {
		return DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from Build"));
	}
}
