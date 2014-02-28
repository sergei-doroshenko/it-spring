package org.training.issuetracker.data.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Comment;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.CommentDAO;
import org.training.issuetracker.exceptions.DaoException;

public class CommentImpDB implements CommentDAO {
	private Connection connection;

	@Override
	public List<Comment> getCommentsList(long issueId) throws DaoException {
		List<Comment> list = new ArrayList<Comment>();
		PreparedStatement select = null;
		ResultSet rs = null;
		try {
			
			select = connection.prepareStatement(SQL_SELECT_COMMENTS_LIST);
			select.setLong(ISSUE_ID_INDEX, issueId);
			rs = select.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setId(rs.getLong("comment_id"));
				comment.setIssueId(rs.getLong("comment_issue_id"));
				comment.setCreateDate(rs.getDate("comment_create_date"));
				User createBy = new User();
				createBy.setId(rs.getLong("create_by_id"));
				createBy.setFirstName(rs.getString("create_by_first_name"));
				createBy.setLastName(rs.getString("create_by_last_name"));
				comment.setCreateBy(createBy);
				comment.setComment(rs.getString("comment_value"));
				list.add(comment);
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} 

	}

	private static final int ISSUE_ID_INDEX = 1;

	private static final String SQL_SELECT_COMMENTS_LIST =
			"SELECT COMMENTS.ID AS comment_id,"
			+ "COMMENTS.ISSUE_ID AS comment_issue_id,"
			+ "COMMENTS.CREATE_DATE AS comment_create_date,"
			+ "COMMENTS.CREATE_BY AS comment_create_by,"
			+ "COMMENTS.COMMENT AS comment_value,"
			+ "USERS.ID AS create_by_id,"
			+ "USERS.FIRST_NAME AS create_by_first_name,"
			+ "USERS.LAST_NAME AS create_by_last_name "
			+ "FROM COMMENTS "
			+ "LEFT JOIN USERS ON COMMENTS.CREATE_BY = USERS.ID "
			+ "WHERE COMMENTS.ISSUE_ID = ?";


}
