package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.User;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.SearchFilterParams;

public interface UserDAO {
	
	List<User> getUsersList(SearchFilterParams params) throws DaoException;
	
	List<User> getUsersList() throws DaoException;

	int getUserRecordsCount() throws DaoException;

	User getUser(String login, String password) throws DaoException;

	User getUser(long id)  throws DaoException;

	long insertUser(User user) throws DaoException;

	void updateUser(User user) throws DaoException;
	
	void deleteUser(long id) throws DaoException;

}
