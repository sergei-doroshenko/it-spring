package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.exceptions.DaoException;

public interface UserDAO {
	
	List<User> getUsersList(int page, int rows, String sidx, String sord) throws DaoException;

	int getUserRecordsCount() throws DaoException;

	User getUser(String login, String password) throws DaoException;

	User getUser(long id)  throws DaoException;

	long insertUser(User user) throws DaoException;

	void updateUser(User user) throws DaoException;
	
	void deleteUser(long id) throws DaoException;

}
