package org.training.issuetracker.data.hiber;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;

public class UserImplHiber implements UserDAO {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getUsersList(int page, int rows, String sidx, String sord)
			throws DaoException {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		
		int firstResult = (page - 1) * rows;
		
		List result = hibernateTemplate.findByCriteria(criteria, firstResult, rows);
//		Session session = sessionFactory.openSession();
//		Criteria cr = session.createCriteria(User.class);
//		int maxResult = 2;
//		cr.setMaxResults(maxResult);
//		List<User> result = cr.list();
//		session.close();
		return Collections.checkedList(result, User.class);
	}

	@Override
	public int getUserRecordsCount() throws DaoException {
		
		return DataAccessUtils.intResult(hibernateTemplate.find("select count(*) from User"));
	}

	
	public List getUsersList() throws DaoException {
		return hibernateTemplate.find("from User");
	}

	@Override
	public User getUser(String login, String password) throws DaoException {
		
	   return (User) DataAccessUtils.requiredUniqueResult(hibernateTemplate.find("from User where email=? and password=?",login, password));

	}

	@Override
	public User getUser(long id) throws DaoException {
		
		return hibernateTemplate.get(User.class, id);
	}

	@Override
	public long insertUser(User user) throws DaoException {
		
		return (Long) hibernateTemplate.save(user);
	}

	@Override
	public void updateUser(User user) throws DaoException {
		
		hibernateTemplate.update(user);
	}

	@Override
	public void deleteUser(long id) throws DaoException {
		
		hibernateTemplate.delete(hibernateTemplate.get(User.class, id));
	}
}
