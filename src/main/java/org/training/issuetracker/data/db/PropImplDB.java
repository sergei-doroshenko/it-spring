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
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.Priority;
import org.training.issuetracker.domain.Resolution;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.Status;
import org.training.issuetracker.domain.Type;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.ConnectionProvider;

public class PropImplDB implements PropDAO {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.data");
	private Connection connection;

	private static final String SQL_SELECT_STATUSES_LIST =
			"SELECT STATUSES.ID AS id,"
			+ "STATUSES.NAME AS name "
			+ "FROM STATUSES";

	private static final String SQL_SELECT_RESOLUTIONS_LIST =
			"SELECT RESOLUTIONS.ID AS id,"
			+ "RESOLUTIONS.NAME AS name "
			+ "FROM RESOLUTIONS";

	private static final String SQL_SELECT_PRIORITIES_LIST =
			"SELECT PRIORITIES.ID AS id,"
			+ "PRIORITIES.NAME AS name "
			+ "FROM PRIORITIES";

	private static final String SQL_SELECT_TYPES_LIST =
			"SELECT TYPES.ID AS id,"
			+ "TYPES.NAME AS name "
			+ "FROM TYPES";
	
	private static final String SQL_SELECT_ROLES_LIST = 
			"SELECT ROLES.ID AS id,"
			+ "ROLES.NAME AS name "
			+ "FROM ROLES";
			
			
	private static final String SQL_SELECT_STATUS =
			"SELECT STATUSES.ID AS id,"
			+ "STATUSES.NAME AS name "
			+ "FROM STATUSES "
			+ "WHERE STATUSES.ID = ?";

	private static final String SQL_SELECT_RESOLUTION =
			"SELECT RESOLUTIONS.ID AS id,"
			+ "RESOLUTIONS.NAME AS name "
			+ "FROM RESOLUTIONS "
			+ "WHERE RESOLUTIONS.ID = ?";

	private static final String SQL_SELECT_PRIORITY =
			"SELECT PRIORITIES.ID AS id,"
			+ "PRIORITIES.NAME AS name "
			+ "FROM PRIORITIES "
			+ "WHERE PRIORITIES.ID = ?";

	private static final String SQL_SELECT_TYPE =
			"SELECT TYPES.ID AS id,"
			+ "TYPES.NAME AS name "
			+ "FROM TYPES "
			+ "WHERE TYPES.ID = ?";
	
	private static final String SQL_SELECT_ROLE =
			"SELECT ROLES.ID AS id,"
			+ "ROLES.NAME AS name "
			+ "FROM ROLES "
			+ "WHERE ROLES.ID = ?";

	private static final int SELECT_PROP_INDEX = 1;
	
	private static final String SQL_INSERT_STATUS = "INSERT INTO STATUSES (NAME) VALUES (?)";
	
	private static final String SQL_INSERT_RESOLUTION = "INSERT INTO RESOLUTIONS (NAME) VALUES (?)";
	
	private static final String SQL_INSERT_PRIORITY = "INSERT INTO PRIORITIES (NAME) VALUES (?)";
	
	private static final String SQL_INSERT_TYPE = "INSERT INTO TYPES (NAME) VALUES (?)";
	
	private static final String SQL_INSERT_ROLE = "INSERT INTO ROLES (NAME) VALUES (?)";
	
	private static final int INSERT_PROP_INDEX = 1;
	
	private static final String SQL_UPDATE_STATUS = 
			"UPDATE STATUSES SET STATUSES.NAME = ? WHERE STATUSES.ID = ?";
	
	private static final String SQL_UPDATE_RESOLUTION = 
			"UPDATE RESOLUTIONS SET RESOLUTIONS.NAME = ? WHERE RESOLUTIONS.ID = ?";
	
	private static final String SQL_UPDATE_PRIORITY = 
			"UPDATE PRIORITIES SET PRIORITIES.NAME = ? WHERE PRIORITIES.ID = ?";
	
	private static final String SQL_UPDATE_TYPE = 
			"UPDATE TYPES SET TYPES.NAME = ? WHERE TYPES.ID = ?";
	
	private static final String SQL_UPDATE_ROLE = 
			"UPDATE ROLES SET ROLES.NAME = ? WHERE ROLES.ID = ?";
	
	private static final int UPDATE_PROP_NAME_IND = 1;
	
	private static final int UPDATE_PROP_ID_IND = 2;
	
	private static final String SQL_DELETE_STATUS = "DELETE FROM STATUSES WHERE STATUSES.ID = ?";
	
	private static final String SQL_DELETE_RESOLUTION = "DELETE FROM RESOLUTIONS WHERE RESOLUTIONS.ID = ?";
	
	private static final String SQL_DELETE_PRIORITY = "DELETE FROM PRIORITIES WHERE PRIORITIES.ID = ?";
	
	private static final String SQL_DELETE_TYPE = "DELETE FROM TYPES WHERE TYPES.ID = ?";
	
	private static final String SQL_DELETE_ROLE = "DELETE FROM ROLES WHERE ROLES.ID = ?";
	
	private static final int DELETE_PROP_ID_IND = 1;
	
	private AbstractPersistentObj createPropObj (PropertyType prop, long id, String name) {
		AbstractPersistentObj propObject = null;
		switch (prop) {
			case STATUS : {
				propObject = new Status(id, name);
				break;
			}
			case RESOLUTION : {
				propObject = new Resolution(id, name);
				break;
			}
			case PRIORITY : {
				propObject = new Priority(id, name);
				break;
			}
			case TYPE : {
				propObject = new Type(id, name);
				break;
			}
			case ROLE : {
				propObject = new Role(id, name);
				break;
			}
			default : {
				break;
			}
		}
		return propObject;
	}

	private AbstractPersistentObj getEmptyPropObj(PropertyType prop) {
		AbstractPersistentObj propObject = null;
		switch (prop) {
			case STATUS : {
				propObject = new Status();
				break;
			}
			case RESOLUTION : {
				propObject = new Resolution();
				break;
			}
			case PRIORITY : {
				propObject = new Priority();
				break;
			}
			case TYPE : {
				propObject = new Type();
				break;
			}
			case ROLE : {
				propObject = new Role();
				break;
			}
			default : {
				break;
			}
		}
		return propObject;
	}

	private String getSelectListQuery(PropertyType prop) {
		String querySelectList = null;
		switch (prop) {
			case STATUS : {
				querySelectList = SQL_SELECT_STATUSES_LIST;
				break;
			}
			case RESOLUTION : {
				querySelectList = SQL_SELECT_RESOLUTIONS_LIST;
				break;
			}
			case PRIORITY : {
				querySelectList = SQL_SELECT_PRIORITIES_LIST;
				break;
			}
			case TYPE : {
				querySelectList = SQL_SELECT_TYPES_LIST;
				break;
			}
			case ROLE : {
				querySelectList = SQL_SELECT_ROLES_LIST;
				break;
			}
			default : {
				break;
			}
		}
		return querySelectList;
	}

	private String getSelectPropQuery(PropertyType prop) {
		String query = null;
		switch (prop) {
			case STATUS : {
				query = SQL_SELECT_STATUS;
				break;
			}
			case RESOLUTION : {
				query = SQL_SELECT_RESOLUTION;
				break;
			}
			case PRIORITY : {
				query = SQL_SELECT_PRIORITY;
				break;
			}
			case TYPE : {
				query = SQL_SELECT_TYPE;
				break;
			}
			case ROLE : {
				query = SQL_SELECT_ROLE;
				break;
			}
			default : {
				break;
			}
		}
		return query;
	}
	
	private String getInsertPropQuery(PropertyType prop) {
		String query = null;
		switch (prop) {
			case STATUS : {
				query = SQL_INSERT_STATUS;
				break;
			}
			case RESOLUTION : {
				query = SQL_INSERT_RESOLUTION;
				break;
			}
			case PRIORITY : {
				query = SQL_INSERT_PRIORITY;
				break;
			}
			case TYPE : {
				query = SQL_INSERT_TYPE;
				break;
			}
			case ROLE : {
				query = SQL_INSERT_ROLE;
				break;
			}
			default : {
				break;
			}
		}
		return query;
	}
	
	private String getUpdatePropQuery(PropertyType prop) {
		String query = null;
		switch (prop) {
			case STATUS : {
				query = SQL_UPDATE_STATUS;
				break;
			}
			case RESOLUTION : {
				query = SQL_UPDATE_RESOLUTION;
				break;
			}
			case PRIORITY : {
				query = SQL_UPDATE_PRIORITY;
				break;
			}
			case TYPE : {
				query = SQL_UPDATE_TYPE;
				break;
			}
			case ROLE : {
				query = SQL_UPDATE_ROLE;
				break;
			}
			default : {
				break;
			}
		}
		return query;
	}
	
	private String getDeletePropQuery(PropertyType prop) {
		String query = null;
		switch (prop) {
			case STATUS : {
				query = SQL_DELETE_STATUS;
				break;
			}
			case RESOLUTION : {
				query = SQL_DELETE_RESOLUTION;
				break;
			}
			case PRIORITY : {
				query = SQL_DELETE_PRIORITY;
				break;
			}
			case TYPE : {
				query = SQL_DELETE_TYPE;
				break;
			}
			case ROLE : {
				query = SQL_DELETE_ROLE;
				break;
			}
			default : {
				break;
			}
		}
		return query;
	}
	
	public enum PropertyType {
		STATUS, RESOLUTION, PRIORITY, TYPE, ROLE
	}

	@Override
	public List<AbstractPersistentObj> getPropList(PropertyType prop) throws DaoException {

		List<AbstractPersistentObj> list = new ArrayList<AbstractPersistentObj>();
		Statement select = null;
		ResultSet rs = null;

		try {
			connection = ConnectionProvider.getConnection();
			select = connection.createStatement();

			rs = select.executeQuery(getSelectListQuery(prop));

			while (rs.next()) {
				AbstractPersistentObj selectObj = createPropObj(prop, rs.getLong("id"), rs.getString("name"));
				list.add(selectObj);
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
	public AbstractPersistentObj getProp(PropertyType prop, long id) throws DaoException {
		AbstractPersistentObj propObject = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		try {
			connection = ConnectionProvider.getConnection();
			select = connection.prepareStatement(getSelectPropQuery(prop));
			select.setLong(SELECT_PROP_INDEX, id);
			rs = select.executeQuery();

			while (rs.next()) {
				propObject =  createPropObj(prop, rs.getLong("id"), rs.getString("name"));
			}

			return propObject;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(select);
		}
	}

	@Override
	public long insertProp(PropertyType prop, AbstractPersistentObj propObject)
			throws DaoException {
		
		PreparedStatement st = null;

		try {
			connection = ConnectionProvider.getConnection();
			st = connection.prepareStatement(getInsertPropQuery(prop));
			st.setString(INSERT_PROP_INDEX, propObject.getName());
						
			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(st);
		}
	}

	@Override
	public long updateProp(PropertyType prop, AbstractPersistentObj propObject)
			throws DaoException {
		
		PreparedStatement st = null;

		try {
			connection = ConnectionProvider.getConnection();
			st = connection.prepareStatement(getUpdatePropQuery(prop));
			st.setString(UPDATE_PROP_NAME_IND, propObject.getName());
			st.setLong(UPDATE_PROP_ID_IND, propObject.getId());
						
			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage(), e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(st);
		}
	}

	@Override
	public long deleteProp(PropertyType prop, long id) throws DaoException {
		
		PreparedStatement st = null;

		try {
			connection = ConnectionProvider.getConnection();
			st = connection.prepareStatement(getDeletePropQuery(prop));
			st.setLong(DELETE_PROP_ID_IND, id);

			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e.getLocalizedMessage());
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(st);
		}
	}

}
