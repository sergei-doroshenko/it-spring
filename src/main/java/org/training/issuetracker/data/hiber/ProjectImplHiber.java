package org.training.issuetracker.data.hiber;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.exceptions.DaoException;

public class ProjectImplHiber implements ProjectDAO {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List getProjectsList() throws DaoException {
		
		return hibernateTemplate.find("from Project");
	}

	@Override
	public Project getProject(long id) throws DaoException {
		
		return (Project) hibernateTemplate.get(Project.class, id);
	}

	@Override
	public long insertProject(Project project) throws DaoException {
		
		return (Long) hibernateTemplate.save(project);
	}

	@Override
	public void updateProject(Project project) throws DaoException {
		
		hibernateTemplate.update(project);
	}

	@Override
	public void deleteProject(long id) throws DaoException {
		
		hibernateTemplate.delete(hibernateTemplate.get(Project.class, id));
	}
	
}
