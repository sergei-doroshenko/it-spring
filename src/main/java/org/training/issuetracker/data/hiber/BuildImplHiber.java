package org.training.issuetracker.data.hiber;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.DAO.BuildDAO;
import org.training.issuetracker.exceptions.DaoException;

public class BuildImplHiber implements BuildDAO {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List getBuildsList() throws DaoException {
		
		return hibernateTemplate.find("from Build");
	}

	@Override
	public List getProjectBuilds(long id) throws DaoException {
		
		return hibernateTemplate.find("from Build where projectId=?", id);
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

}
