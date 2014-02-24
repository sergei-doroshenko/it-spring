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
import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.Priority;
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.domain.Resolution;
import org.training.issuetracker.domain.Status;
import org.training.issuetracker.domain.Type;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.ConnectionProvider;

public class IssueImplDB implements IssueDAO {
	private static final int SELECT_ISSUE_INDEX = 1;

	private static final int SELECT_ISSUE_LIST_USER_ID_INDEX = 1;

	private static final String SQL_SELECT_ISSUE =
			"SELECT ISSUES.ID AS issue_id, ISSUES.CREATE_DATE AS create_date,"
			+ "ISSUES.CREATE_BY AS create_by_id,"
			+ "(SELECT USERS.FIRST_NAME FROM USERS where USERS.ID = ISSUES.CREATE_BY) AS create_by_first_name,"
			+ "(SELECT USERS.LAST_NAME FROM USERS where USERS.ID = ISSUES.CREATE_BY) AS create_by_last_name,"
			+ "ISSUES.MODIFY_DATE AS modity_date,"
			+ "ISSUES.MODIFIED_BY AS modify_by_id,"
			+ "(SELECT USERS.FIRST_NAME FROM USERS where USERS.ID = ISSUES.MODIFIED_BY) AS modify_by_first_name,"
			+ "(SELECT USERS.LAST_NAME FROM USERS where USERS.ID = ISSUES.MODIFIED_BY) AS modifiy_by_last_name,"
			+ "ISSUES.TYPE_ID AS type_id, TYPES.NAME AS type_name,"
			+ "ISSUES.PRIORITY_ID AS priority_id, PRIORITIES.NAME AS priority_name,"
			+ "ISSUES.STATUS_ID AS status_id, STATUSES.NAME AS status_name,"
			+ "ISSUES.RESOLUTION_ID AS resolution_id, RESOLUTIONS.NAME AS resolution_name,"
			+ "ISSUES.BUILD_ID AS build_id, BUILDS.NAME AS build_name,"
			+ "BUILDS.PROJECT_ID AS project_id, PROJECTS.NAME AS project_name,"
			+ "ISSUES.ASSIGNEE_ID AS assignee_id, USERS.FIRST_NAME AS assignee_first_name,"
			+ "USERS.LAST_NAME AS assignee_last_name,"
			+ "ISSUES.SUMMARY AS summary, ISSUES.DESCRIPTION AS description "
			+ "FROM ISSUES "
			+ "LEFT JOIN TYPES ON ISSUES.TYPE_ID = TYPES.ID "
			+ "LEFT JOIN PRIORITIES ON ISSUES.PRIORITY_ID = PRIORITIES.ID "
			+ "LEFT JOIN STATUSES ON STATUSES.ID = ISSUES.STATUS_ID "
			+ "LEFT JOIN RESOLUTIONS ON ISSUES.RESOLUTION_ID = RESOLUTIONS.ID "
			+ "LEFT JOIN BUILDS ON ISSUES.BUILD_ID = BUILDS.ID "
			+ "LEFT JOIN PROJECTS ON BUILDS.PROJECT_ID = PROJECTS.ID "
			+ "LEFT JOIN USERS ON ISSUES.ASSIGNEE_ID = USERS.ID "
			+ "WHERE ISSUES.ID = ?";

	private static String SQL_SELECT_ISSUE_LIST =
			"SELECT ISSUES.ID AS issue_id, ISSUES.CREATE_DATE AS create_date,"
			+ "ISSUES.CREATE_BY AS create_by_id,"
			+ "(SELECT USERS.FIRST_NAME FROM USERS where USERS.ID = ISSUES.CREATE_BY) AS create_by_first_name,"
			+ "(SELECT USERS.LAST_NAME FROM USERS where USERS.ID = ISSUES.CREATE_BY) AS create_by_last_name,"
			+ "ISSUES.MODIFY_DATE AS modity_date,"
			+ "ISSUES.MODIFIED_BY AS modify_by_id,"
			+ "(SELECT USERS.FIRST_NAME FROM USERS where USERS.ID = ISSUES.MODIFIED_BY) AS modify_by_first_name,"
			+ "(SELECT USERS.LAST_NAME FROM USERS where USERS.ID = ISSUES.MODIFIED_BY) AS modifiy_by_last_name,"
			+ "ISSUES.TYPE_ID AS type_id, TYPES.NAME AS type_name,"
			+ "ISSUES.PRIORITY_ID AS priority_id, PRIORITIES.NAME AS priority_name,"
			+ "ISSUES.STATUS_ID AS status_id, STATUSES.NAME AS status_name,"
			+ "ISSUES.RESOLUTION_ID AS resolution_id, RESOLUTIONS.NAME AS resolution_name,"
			+ "ISSUES.BUILD_ID AS build_id, BUILDS.NAME AS build_name,"
			+ "BUILDS.PROJECT_ID AS project_id, PROJECTS.NAME AS project_name,"
			+ "ISSUES.ASSIGNEE_ID AS assignee_id, USERS.FIRST_NAME AS assignee_first_name,"
			+ "USERS.LAST_NAME AS assignee_last_name,"
			+ "ISSUES.SUMMARY AS summary, ISSUES.DESCRIPTION AS description "
			+ "FROM ISSUES "
			+ "LEFT JOIN TYPES ON ISSUES.TYPE_ID = TYPES.ID "
			+ "LEFT JOIN PRIORITIES ON ISSUES.PRIORITY_ID = PRIORITIES.ID "
			+ "LEFT JOIN STATUSES ON STATUSES.ID = ISSUES.STATUS_ID "
			+ "LEFT JOIN RESOLUTIONS ON ISSUES.RESOLUTION_ID = RESOLUTIONS.ID "
			+ "LEFT JOIN BUILDS ON ISSUES.BUILD_ID = BUILDS.ID "
			+ "LEFT JOIN PROJECTS ON BUILDS.PROJECT_ID = PROJECTS.ID "
			+ "LEFT JOIN USERS ON ISSUES.ASSIGNEE_ID = USERS.ID";

	private static final String SQL_SELECT_ISSUE_LIST_TAIL =
			" WHERE ISSUES.ASSIGNEE_ID = ?";

	private static final String SQL_IS_ID_SELECT =
			"SELECT ISSUES.ID AS issue_id FROM ISSUES WHERE ISSUES.ID = ?";

	private static final String SQL_UPDATE_ISSUE =
			"UPDATE ISSUES SET "
			+ "ISSUES.MODIFY_DATE = CURRENT_DATE,"
			+ "ISSUES.MODIFIED_BY = ?,"
			+ "ISSUES.TYPE_ID = ?,"
			+ "ISSUES.PRIORITY_ID = ?,"
			+ "ISSUES.STATUS_ID = ?,"
			+ "ISSUES.RESOLUTION_ID = ?,"
			+ "ISSUES.BUILD_ID = ?,"
			+ "ISSUES.PROJECT_ID = ?,"
			+ "ISSUES.ASSIGNEE_ID = ?,"
			+ "ISSUES.SUMMARY = ?,"
			+ "ISSUES.DESCRIPTION = ?"
			+ "WHERE ISSUES.ID = ?";

	private static final int UPDATE_ISSUE_MODIFY_BY_IND = 1;

	private static final int UPDATE_ISSUE_TYPE_ID_IND = 2;

	private static final int UPDATE_ISSUE_PRIORITY_ID_IND = 3;

	private static final int UPDATE_ISSUE_STATUS_ID_IND = 4;

	private static final int UPDATE_ISSUE_RESOLUTION_ID_IND = 5;

	private static final int UPDATE_ISSUE_BUILD_ID_IND = 6;

	private static final int UPDATE_ISSUE_PROJECT_ID_IND = 7;

	private static final int UPDATE_ISSUE_ASSIGNEE_ID_IND = 8;

	private static final int UPDATE_ISSUE_SUMMARY_IND = 9;

	private static final int UPDATE_ISSUE_DESCRIPTION_IND = 10;

	private static final int UPDATE_ISSUE_ID_IND = 11;

	private static final String SQL_INSERT_ISSUE =
			"INSERT INTO ISSUES (CREATE_DATE, CREATE_BY, STATUS_ID, TYPE_ID, PRIORITY_ID,"
			+ "PROJECT_ID, BUILD_ID, SUMMARY, DESCRIPTION, ASSIGNEE_ID) "
			+ "VALUES (CURRENT_DATE, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final int INSERT_ISSUE_CREATE_BY_IND = 1;

	private static final int INSERT_ISSUE_STATUS_ID_IND = 2;

	private static final int INSERT_ISSUE_TYPE_BY_IND = 3;

	private static final int INSERT_ISSUE_PRIORITY_BY_IND = 4;

	private static final int INSERT_ISSUE_PROJECT_ID_BY_IND = 5;

	private static final int INSERT_ISSUE_BUILD_ID_IND = 6;

	private static final int INSERT_ISSUE_SUMMARY_IND = 7;

	private static final int INSERT_ISSUE_DESCRIPTION_IND = 8;

	private static final int INSERT_ISSUE_ASSIGNEE_ID_IND = 9;

	private static final String SQL_SELECT_LAST_ISSUE_ID = "SELECT IDENTITY_VAL_LOCAL() FROM ISSUES";

	private static final String SQL_DELETE_ISSUE = "DELETE FROM ISSUES WHERE ISSUES.ID = ?";

	private static final int DELETE_ISSUE_INDEX = 1;

	private final Logger logger = Logger.getLogger("org.training.issuetracker.data");
	private Connection connection;

	@Override
	public Issue getIssueById(long id) throws DaoException {
		Issue issue = new Issue();
		PreparedStatement selectIssue = null;
		ResultSet rs = null;
		try {
			connection = ConnectionProvider.getConnection();
			selectIssue = connection.prepareStatement(SQL_SELECT_ISSUE);
			selectIssue.setLong(SELECT_ISSUE_INDEX, id);
			rs = selectIssue.executeQuery();
			while (rs.next()) {
				issue.setId(rs.getLong("issue_id"));
				issue.setCreateDate(rs.getDate("create_date"));
				User createBy = new User();
				createBy.setId(rs.getLong("create_by_id"));
				createBy.setFirstName(rs.getString("create_by_first_name"));
				createBy.setLastName(rs.getString("create_by_last_name"));
				issue.setCreateBy(createBy);
				issue.setModifyDate(rs.getDate("modity_date"));
				User modifyBy = new User();
				modifyBy.setId(rs.getLong("modify_by_id"));
				modifyBy.setFirstName(rs.getString("modify_by_first_name"));
				modifyBy.setLastName(rs.getString("modifiy_by_last_name"));
				issue.setModifyBy(modifyBy);
				Type type = new Type(rs.getLong("type_id"), rs.getString("type_name"));
				issue.setType(type);
				Priority priority = new Priority(rs.getLong("priority_id"), rs.getString("priority_name"));
				issue.setPriority(priority);
				Status status = new Status(rs.getLong("status_id"), rs.getString("status_name"));
				issue.setStatus(status);
				Resolution resolution = new Resolution(rs.getLong("resolution_id"), rs.getString("resolution_name"));
				issue.setResolution(resolution);
				Build build = new Build(rs.getLong("build_id"), rs.getString("build_name"), rs.getLong("project_id"));
				issue.setBuild(build);
				Project project = new Project();
				project.setId(rs.getLong("project_id"));
				project.setName(rs.getString("project_name"));
				issue.setProject(project);
				User assignee = new User();
				assignee.setId(rs.getLong("assignee_id"));
				assignee.setFirstName(rs.getString("assignee_first_name"));
				assignee.setLastName(rs.getString("assignee_last_name"));
				issue.setAssignee(assignee);
				issue.setSummary(rs.getString("summary"));
				issue.setDescription(rs.getString("description"));

			}

			return issue;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(selectIssue);
		}
	}

	@Override
	public List<Issue> getIssueList(User user) throws DaoException {
		
		List<Issue> list = new ArrayList<Issue>();
		PreparedStatement select = null;
		ResultSet rs = null;
		String query = SQL_SELECT_ISSUE_LIST;

		if (user != null) {
			query += SQL_SELECT_ISSUE_LIST_TAIL;
		}

		try {
			connection = ConnectionProvider.getConnection();
			select = connection.prepareStatement(query);

			if (user != null) {
				select.setLong(SELECT_ISSUE_LIST_USER_ID_INDEX, user.getId());
			}

			rs = select.executeQuery();
			while (rs.next()) {
				Issue issue = new Issue();
				issue.setId(rs.getLong("issue_id"));
				issue.setCreateDate(rs.getDate("create_date"));
				User createBy = new User();
				createBy.setId(rs.getLong("create_by_id"));
				createBy.setFirstName(rs.getString("create_by_first_name"));
				createBy.setLastName(rs.getString("create_by_last_name"));
				issue.setCreateBy(createBy);
				issue.setModifyDate(rs.getDate("modity_date"));
				User modifyBy = new User();
				modifyBy.setId(rs.getLong("modify_by_id"));
				modifyBy.setFirstName(rs.getString("modify_by_first_name"));
				modifyBy.setLastName(rs.getString("modifiy_by_last_name"));
				issue.setModifyBy(modifyBy);
				Type type = new Type(rs.getLong("type_id"), rs.getString("type_name"));
				issue.setType(type);
				Priority priority = new Priority(rs.getLong("priority_id"), rs.getString("priority_name"));
				issue.setPriority(priority);
				Status status = new Status(rs.getLong("status_id"), rs.getString("status_name"));
				issue.setStatus(status);
				Resolution resolution = new Resolution(rs.getLong("resolution_id"), rs.getString("resolution_name"));
				issue.setResolution(resolution);
				Build build = new Build(rs.getLong("build_id"), rs.getString("build_name"), rs.getLong("project_id"));
				issue.setBuild(build);
				Project project = new Project();
				project.setId(rs.getLong("project_id"));
				project.setName(rs.getString("project_name"));
				issue.setProject(project);
				User assignee = new User();
				assignee.setId(rs.getLong("assignee_id"));
				assignee.setFirstName(rs.getString("assignee_first_name"));
				assignee.setLastName(rs.getString("assignee_last_name"));
				issue.setAssignee(assignee);
				issue.setSummary(rs.getString("summary"));
				issue.setDescription(rs.getString("description"));
				list.add(issue);
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
	public boolean isId(long id) throws DaoException {
		long findId = 0;
		PreparedStatement select = null;
		ResultSet rs = null;
		try {
			connection = ConnectionProvider.getConnection();
			select = connection.prepareStatement(SQL_IS_ID_SELECT);
			select.setLong(SELECT_ISSUE_INDEX, id);
			rs = select.executeQuery();

			while (rs.next()) {
				findId = rs.getLong("issue_id");
			}

			return findId > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(select);
		}
	}

	@Override
	public long insertIssue(Issue issue) throws DaoException {
		PreparedStatement insert = null;
		Statement select = null;
		ResultSet rs = null;

		try {
			connection = ConnectionProvider.getConnection();
			insert = connection.prepareStatement(SQL_INSERT_ISSUE);
			insert.setLong(INSERT_ISSUE_CREATE_BY_IND, issue.getCreateBy().getId());
			insert.setLong(INSERT_ISSUE_STATUS_ID_IND, issue.getStatus().getId());
			insert.setLong(INSERT_ISSUE_TYPE_BY_IND, issue.getType().getId());
			insert.setLong(INSERT_ISSUE_PRIORITY_BY_IND, issue.getPriority().getId());
			insert.setLong(INSERT_ISSUE_PROJECT_ID_BY_IND, issue.getProject().getId());
			insert.setLong(INSERT_ISSUE_BUILD_ID_IND, issue.getBuild().getId());
			insert.setString(INSERT_ISSUE_SUMMARY_IND, issue.getSummary());
			insert.setString(INSERT_ISSUE_DESCRIPTION_IND, issue.getDescription());

			if (issue.getAssignee() != null) {
				insert.setLong(INSERT_ISSUE_ASSIGNEE_ID_IND, issue.getAssignee().getId());
			} else {
				insert.setNull(INSERT_ISSUE_ASSIGNEE_ID_IND, java.sql.Types.INTEGER);
			}

			insert.executeUpdate();

			select = connection.createStatement();
			rs = select.executeQuery(SQL_SELECT_LAST_ISSUE_ID);

			long lastId = 0;
			while (rs.next()) {
				lastId = lastId < rs.getLong(1) ? rs.getLong(1) : lastId;
			}
			return lastId;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(insert);
			ConnectionProvider.closeStatemnts(select);
		}
	}

	@Override
	public long updateIssue(Issue issue) throws DaoException {
		PreparedStatement update = null;

		try {
			connection = ConnectionProvider.getConnection();
			update = connection.prepareStatement(SQL_UPDATE_ISSUE);
			update.setLong(UPDATE_ISSUE_MODIFY_BY_IND, issue.getModifyBy().getId());
			update.setLong(UPDATE_ISSUE_TYPE_ID_IND, issue.getType().getId());
			update.setLong(UPDATE_ISSUE_PRIORITY_ID_IND, issue.getPriority().getId());
			update.setLong(UPDATE_ISSUE_STATUS_ID_IND, issue.getStatus().getId());
			update.setLong(UPDATE_ISSUE_RESOLUTION_ID_IND, issue.getResolution().getId());
			update.setLong(UPDATE_ISSUE_BUILD_ID_IND, issue.getBuild().getId());
			update.setLong(UPDATE_ISSUE_PROJECT_ID_IND, issue.getProject().getId());
			update.setLong(UPDATE_ISSUE_ASSIGNEE_ID_IND, issue.getAssignee().getId());
			update.setString(UPDATE_ISSUE_SUMMARY_IND, issue.getSummary());
			update.setString(UPDATE_ISSUE_DESCRIPTION_IND, issue.getDescription());
			update.setLong(UPDATE_ISSUE_ID_IND, issue.getId());

			return update.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(update);
		}

	}

	@Override
	public long deleteIssue(long id) throws DaoException {
		PreparedStatement delete = null;

		try {
			connection = ConnectionProvider.getConnection();
			delete = connection.prepareStatement(SQL_DELETE_ISSUE);
			delete.setLong(DELETE_ISSUE_INDEX, id);

			return delete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(delete);
		}

	}

}
