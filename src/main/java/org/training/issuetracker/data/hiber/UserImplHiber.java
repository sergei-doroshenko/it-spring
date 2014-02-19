package org.training.issuetracker.data.hiber;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
//	public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
	
	private HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) 
    {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	
	@Override
	public List<User> getUsersList() throws DaoException {
		return null;
	}

	@Override
	public User getUser(String login, String password) throws DaoException {
		
		
		
		
//		Session session = sessionFactory.openSession();
//		Query query = session.createQuery("from User where email=? and password=?");
//		query.setParameter(0, login);
//		query.setParameter(1, password);
//		
//		User user = (User) query.uniqueResult();
//		logger.debug("User query result = " + user);
//		return user;
//		return (User) this.sessionFactory.getCurrentSession().createQuery("from USERS where USERS.EMAIL=? and USERS.PASSWORD=?")
//				.setParameter(0, login).setParameter(1, password)
//				.list();
//		return null;
		HibernateTemplate ht = new HibernateTemplate(this.sessionFactory);
	   return (User) ht.find("from User where email=? and password=?",login, password).get(0);
	   
		
	}

	@Override
	public User getUser(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long insertUser(User user) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long updateUser(User user) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteUser(long id) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}
}
