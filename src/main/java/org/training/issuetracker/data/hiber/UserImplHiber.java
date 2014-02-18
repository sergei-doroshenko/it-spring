package org.training.issuetracker.data.hiber;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;

public class UserImplHiber implements UserDAO {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
//	@Autowired
//	private SessionFactory sessionFactory;
	
	@Override
	public List<User> getUsersList() throws DaoException {
		return null;
	}

	@Override
	public User getUser(String login, String password) throws DaoException {
//		logger.debug("Session =================== " + sessionFactory.toString());
//		return (User) this.sessionFactory.getCurrentSession().createQuery("from USERS where USERS.EMAIL=? and USERS.PASSWORD=?")
//				.setParameter(0, login).setParameter(1, password)
//				.list();
		return null;
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
