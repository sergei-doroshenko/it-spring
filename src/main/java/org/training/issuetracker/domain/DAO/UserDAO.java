package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.User;
import org.training.issuetracker.exceptions.DaoException;

public interface UserDAO {

	List<User> getUsersList() throws DaoException;

	User getUser(String login, String password) throws DaoException;

	User getUser(long id)  throws DaoException;

	long insertUser(User user) throws DaoException;

	long updateUser(User user) throws DaoException;
	
	long deleteUser(User user) throws DaoException;

}
