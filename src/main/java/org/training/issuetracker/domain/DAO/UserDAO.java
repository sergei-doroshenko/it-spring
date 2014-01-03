package org.training.issuetracker.domain.DAO;

import java.util.Map;

import org.training.issuetracker.domain.User;
import org.training.issuetracker.exceptions.DaoException;

public interface UserDAO {
	Map<String, User> getUsersMap() throws DaoException;

	User getUser(String login, String password) throws DaoException;
}
