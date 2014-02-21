package org.training.issuetracker.data.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Attachment;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.AttachmentDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.ConnectionProvider;

public class AttchmentImplDB implements AttachmentDAO {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.data");
	private Connection connection;

	private static final String SQL_SELECT_ATTCH_BASE =
			"SELECT ATTACHMENTS.ID AS attachment_id,"
			+ "ATTACHMENTS.CREATE_DATE AS attachment_create_date,"
			+ "ATTACHMENTS.CREATE_BY AS attachment_create_by,"
			+ "USERS.FIRST_NAME AS create_by_first_name,"
			+ "USERS.LAST_NAME AS create_by_last_name,"
			+ "ATTACHMENTS.ISSUE_ID AS attachment_issue_id,"
			+ "ATTACHMENTS.FILE_NAME AS attachment_file_name "
			+ "FROM ATTACHMENTS "
			+ "LEFT JOIN USERS ON ATTACHMENTS.CREATE_BY = USERS.ID ";

	private static final String SQL_SELECT_ATTCH_LIST = SQL_SELECT_ATTCH_BASE
			+ "WHERE ATTACHMENTS.ISSUE_ID = ?";

	private static final String SQL_SELECT_ATTCH = SQL_SELECT_ATTCH_BASE
			+ "WHERE ATTACHMENTS.ID = ?";

	private static final String SQL_INSERT_ATTCH =
			"INSERT INTO ATTACHMENTS (ISSUE_ID, CREATE_DATE, CREATE_BY, FILE_NAME) "
			+ "VALUES (?, ?, ?, ?)";//"VALUES (1, CURRENT_DATE, 4, 'test.txt')";

	private static final int ISSUE_ID_INDEX = 1;

	@Override
	public List<Attachment> getAttachmentsList(long issueId)
			throws DaoException {
		List<Attachment> list = new ArrayList<Attachment>();
		PreparedStatement select = null;
		ResultSet rs = null;
		try {
			connection = null;//ConnectionProvider.getConnection();
			select = connection.prepareStatement(SQL_SELECT_ATTCH_LIST);
			select.setLong(ISSUE_ID_INDEX, issueId);
			rs = select.executeQuery();
			while (rs.next()) {
				Attachment attch = new Attachment();
				attch.setId(rs.getLong("attachment_id"));
				attch.setCreateDate(rs.getDate("attachment_create_date"));
				attch.setIssueId(rs.getLong("attachment_issue_id"));
				User createBy = new User();
				createBy.setId(rs.getLong("attachment_create_by"));
				createBy.setFirstName(rs.getString("create_by_first_name"));
				createBy.setLastName(rs.getString("create_by_last_name"));
				attch.setCreateBy(createBy);
				attch.setUrl(rs.getString("attachment_file_name"));
				list.add(attch);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(select);
		}

	}

	@Override
	public void addAttchment(Attachment attachment) throws DaoException {
		logger.debug("Attachment = " + attachment.getUrl());
		PreparedStatement insert = null;
		try {
			connection = null;//ConnectionProvider.getConnection();
			insert = connection.prepareStatement(SQL_INSERT_ATTCH);
			insert.setLong(1, attachment.getIssueId());
			insert.setDate(2, attachment.getCreateDate());
			insert.setLong(3, attachment.getCreateBy().getId());
			insert.setString(4, attachment.getUrl());
			int result = insert.executeUpdate();
			logger.debug("Attachment insert result = " + result);
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(insert);
		}

	}

	@Override
	public Attachment getAttchment(long attchmentId) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
