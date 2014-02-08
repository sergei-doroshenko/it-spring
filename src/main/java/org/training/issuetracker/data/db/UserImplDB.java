package org.training.issuetracker.data.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.ConnectionProvider;

public class UserImplDB implements UserDAO {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.data");
	private Connection connection;

	private static final String SQL_SELECT_USER =
			"SELECT USERS.ID AS user_id,"
			+ "USERS.FIRST_NAME AS user_first_name,"
			+ "USERS.LAST_NAME AS user_last_name,"
			+ "USERS.EMAIL AS user_login, USERS.PASSWORD AS user_password,"
			+ "ROLES.ID AS role_id, ROLES.RL_NAME AS role_name "
			+ "FROM USERS "
			+ "LEFT JOIN ROLES ON USERS.ROLE_ID = ROLES.ID "
			+ "WHERE USERS.EMAIL = ?"
			+ "AND USERS.PASSWORD = ?";

	private static final int LOGIN_INDEX = 1;

	private static final int PASSWORD_INDEX = 2;

	private static final String SQL_SELECT_USER_BY_ID =
			"SELECT USERS.ID AS user_id,"
			+ "USERS.FIRST_NAME AS user_first_name,"
			+ "USERS.LAST_NAME AS user_last_name,"
			+ "USERS.EMAIL AS user_login, USERS.PASSWORD AS user_password,"
			+ "ROLES.ID AS role_id, ROLES.RL_NAME AS role_name "
			+ "FROM USERS "
			+ "LEFT JOIN ROLES ON USERS.ROLE_ID = ROLES.ID "
			+ "WHERE USERS.ID = ?";

	private static final int ID_INDEX = 1;

	private static final String SELECT_USERS_LIST =
			"SELECT USERS.ID AS user_id,"
			+ "USERS.FIRST_NAME AS user_first_name,"
			+ "USERS.LAST_NAME AS user_last_name,"
			+ "USERS.EMAIL AS user_login, USERS.PASSWORD AS user_password,"
			+ "ROLES.ID AS role_id, ROLES.RL_NAME AS role_name "
			+ "FROM USERS "
			+ "LEFT JOIN ROLES ON USERS.ROLE_ID = ROLES.ID";

	@Override
	public User getUser(String login, String password) throws DaoException {
		User user = new User();
		PreparedStatement selectUser = null;
		ResultSet rs = null;

		try {
			connection = ConnectionProvider.getConnection();
			selectUser = connection.prepareStatement(SQL_SELECT_USER);
			selectUser.setString(LOGIN_INDEX, login);
			selectUser.setString(PASSWORD_INDEX, password);
			rs = selectUser.executeQuery();

			while (rs.next()) {
				user.setId(rs.getLong("user_id"));
				user.setFirstName(rs.getString("user_first_name"));
				user.setLastName(rs.getString("user_last_name"));
				user.setEmail(rs.getString("user_login"));
				user.setPassword(rs.getString("user_password"));
				user.setRole(new Role(rs.getLong("role_id"), rs.getString("role_name")));
			}

			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(selectUser);
		}

	}

	@Override
	public User getUser(long id) throws DaoException {
		User user = new User();
		PreparedStatement selectUser = null;
		ResultSet rs = null;

		try {
			connection = ConnectionProvider.getConnection();
			selectUser = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
			selectUser.setLong(ID_INDEX, id);
			rs = selectUser.executeQuery();

			while (rs.next()) {
				user.setId(rs.getLong("user_id"));
				user.setFirstName(rs.getString("user_first_name"));
				user.setLastName(rs.getString("user_last_name"));
				user.setEmail(rs.getString("user_login"));
				user.setPassword(rs.getString("user_password"));
				user.setRole(new Role(rs.getLong("role_id"), rs.getString("role_name")));
			}

			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(selectUser);
		}

	}

	@Override
	public List<User> getUsersList() throws DaoException {
		List<User> list = new ArrayList<User>();
		Statement select = null;
		ResultSet rs = null;

		try {
			connection = ConnectionProvider.getConnection();
			select = connection.createStatement();
			rs = select.executeQuery(SELECT_USERS_LIST);

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getLong("user_id"));
				user.setFirstName(rs.getString("user_first_name"));
				user.setLastName(rs.getString("user_last_name"));
				user.setEmail(rs.getString("user_login"));
				user.setPassword(rs.getString("user_password"));
				user.setRole(new Role(rs.getLong("role_id"), rs.getString("role_name")));
				list.add(user);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closeStatemnts(select);
		}

	}

	@Override
	public long insertUser(User user) throws DaoException {

		return 205;
	}

	@Override
	public long updateUser(User user) throws DaoException {

		return 205;
	}

	@Override
	public long deleteUser(User user) throws DaoException {
		
		return 207;
	}


}
