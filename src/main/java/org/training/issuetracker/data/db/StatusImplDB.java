package org.training.issuetracker.data.db;

import java.sql.Connection;
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
import org.training.issuetracker.domain.Status;
import org.training.issuetracker.domain.Type;
import org.training.issuetracker.domain.DAO.StatusDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.ConnectionProvider;

public class StatusImplDB implements StatusDAO {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.data");
	private Connection connection;
	private AbstractPersistentObj objectForSelectList;

	private static final String SQL_SELECT_STATUSES_LIST =
			"SELECT STATUSES.ID AS id,"
			+ "STATUSES.ST_NAME AS name "
			+ "FROM STATUSES";

	private static final String SQL_SELECT_RESOLUTIONS_LIST = null;
	private static final String SQL_SELECT_PRIORITIES_LIST = null;
	private static final String SQL_SELECT_TYPES_LIST = null;

	private String selectSelectListQuery(PropertyType prop) {
		String query = null;
		switch (prop) {
			case STATUS : {
				query = SQL_SELECT_STATUSES_LIST;
				objectForSelectList = new Status();
				break;
			}
			case RESOLUTION : {
				query = SQL_SELECT_RESOLUTIONS_LIST;
				objectForSelectList = new Resolution();
				break;
			}
			case PRIORITY : {
				query = SQL_SELECT_PRIORITIES_LIST;
				objectForSelectList = new Priority();
				break;
			}
			case TYPE : {
				query = SQL_SELECT_TYPES_LIST;
				objectForSelectList = new Type();
				break;
			}
			default : {
				break;
			}
		}

		return query;
	}

	public enum PropertyType {
		STATUS, RESOLUTION, PRIORITY, TYPE
	}

	@Override
	public List<AbstractPersistentObj> getStatusesList(PropertyType prop) throws DaoException {
		List<AbstractPersistentObj> list = new ArrayList<AbstractPersistentObj>();
		Statement select = null;
		ResultSet rs = null;

		try {
			connection = ConnectionProvider.getConnection();
			select = connection.createStatement();
			rs = select.executeQuery(selectSelectListQuery(prop));
			logger.debug("Prop object = " + objectForSelectList.getClass().getSimpleName());
			while (rs.next()) {
				objectForSelectList.setId(rs.getLong("id"));
				objectForSelectList.setName(rs.getString("name"));
				list.add(objectForSelectList);
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

}
