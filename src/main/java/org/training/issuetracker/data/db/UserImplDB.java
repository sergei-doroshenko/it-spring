package org.training.issuetracker.data.db;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.i18n.Localizer;
import org.training.issuetracker.i18n.LocalizerFactory;
import org.training.issuetracker.utils.ConnectionProvider;

public class UserImplDB implements UserDAO {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.data");
	private Connection connection;
	private ResourceBundle errors;
	
	public UserImplDB() {
		super();
		
		try {
			Localizer localizer = LocalizerFactory.getLocalizer(new Locale("en", "EN"));
			this.errors = localizer.getBundle("errors");
			logger.debug(errors.getString("issue.err.null"));
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
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
	
	private static final String SQL_INSERT_USER = 
			"INSERT INTO USERS(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, ROLE_ID) "
			 + "VALUES (?, ?, ?, ?, ?)";
	
	private static final int INSERT_USER_FIRST_NAME_IND = 1;
	
	private static final int INSERT_USER_LAST_NAME_IND = 2;
	
	private static final int INSERT_EMAIL_IND = 3;
	
	private static final int INSERT_PASSWORD_IND = 4;
	
	private static final int INSERT_USER_ROLE_ID_IND = 5;
	
	private static final String SQL_UPDATE_USER = 
			"UPDATE USERS SET "
			+ "USERS.FIRST_NAME = ?,"
			+ "USERS.LAST_NAME = ?,"
			+ "USERS.EMAIL = ?,"
			+ "USERS.PASSWORD = ?,"
			+ "USERS.ROLE_ID = ? "
			+ "WHERE USERS.ID = ?";
		
	private static final int UPDATE_USER_FIRST_NAME_IND = 1;
	
	private static final int UPDATE_USER_LAST_NAME_IND = 2;
	
	private static final int UPDATE_EMAIL_IND = 3;
	
	private static final int UPDATE_PASSWORD_IND = 4;
	
	private static final int UPDATE_USER_ROLE_ID_IND = 5;
	
	private static final int UPDATE_USER_ID_IND = 6;
	
	private static final String SQL_DELETE_USER = 
			"DELETE FROM USERS WHERE USERS.ID = ?";
	
	private static final int DELETE_USER_ID_IND = 1;
	
	private static final String SQL_SELECT_LAST_USER_ID = "SELECT IDENTITY_VAL_LOCAL() FROM USERS";
	
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
			throw new DaoException(Constants.ERROR_FIND_USER, e);
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
		PreparedStatement st = null;
		Statement select = null;
		ResultSet rs = null;

		try {
			connection = ConnectionProvider.getConnection();
			st = connection.prepareStatement(SQL_INSERT_USER);
			st.setString(INSERT_USER_FIRST_NAME_IND, user.getFirstName());
			st.setString(INSERT_USER_LAST_NAME_IND, user.getLastName());
			st.setString(INSERT_EMAIL_IND, user.getEmail());
			st.setString(INSERT_PASSWORD_IND, user.getPassword());
			st.setLong(INSERT_USER_ROLE_ID_IND, user.getRole().getId());
			
			synchronized(UserImplDB.class){
				st.executeUpdate();
			}
						
			select = connection.createStatement();
			rs = select.executeQuery(SQL_SELECT_LAST_USER_ID);

			long lastId = 0;
			while (rs.next()) {
				lastId = lastId < rs.getLong(1) ? rs.getLong(1) : lastId;
			}
			return lastId;
		} catch (SQLException e) {
			e.printStackTrace();
			String errorMessage = e.getMessage();
			if(errors != null) {
				errorMessage = errors.getString("user.err.duplicate");
			}
			throw new DaoException(errorMessage, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(st);
			ConnectionProvider.closeStatemnts(select);
		}
	}

	@Override
	public long updateUser(User user) throws DaoException {
		PreparedStatement st = null;

		try {
			connection = ConnectionProvider.getConnection();
			st = connection.prepareStatement(SQL_UPDATE_USER);
			st.setString(UPDATE_USER_FIRST_NAME_IND, user.getFirstName());
			st.setString(UPDATE_USER_LAST_NAME_IND, user.getLastName());
			st.setString(UPDATE_EMAIL_IND, user.getEmail());
			st.setString(UPDATE_PASSWORD_IND, user.getPassword());
			st.setLong(UPDATE_USER_ROLE_ID_IND, user.getRole().getId());
			st.setLong(UPDATE_USER_ID_IND, user.getId());
						
			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(st);
		}
	}

	@Override
	public long deleteUser(long id) throws DaoException {
		PreparedStatement st = null;

		try {
			connection = ConnectionProvider.getConnection();
			st = connection.prepareStatement(SQL_DELETE_USER);
			st.setLong(DELETE_USER_ID_IND, id);

			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(st);
		}
	}

}
