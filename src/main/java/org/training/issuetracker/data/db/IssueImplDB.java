package org.training.issuetracker.data.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	private final Logger logger = Logger.getLogger("org.training.issuetracker.data");
	private Connection connection;

	@Override
	public Issue getIssue(long id) throws DaoException {
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
			logger.debug("User in sql = " + user.getEmail());
			query += SQL_SELECT_ISSUE_LIST_TAIL;
		}
		logger.debug(query);
		try {
			connection = ConnectionProvider.getConnection();
			select = connection.prepareStatement(query);

			if (user != null) {
				logger.debug("User Id in sql = " + user.getId());
				select.setLong(SELECT_ISSUE_LIST_USER_ID_INDES, user.getId());
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

	private static final int SELECT_ISSUE_INDEX = 1;

	private static final int SELECT_ISSUE_LIST_USER_ID_INDES = 1;

	private static final String SQL_SELECT_ISSUE =
			"SELECT ISSUES.ID AS issue_id, ISSUES.CREATE_DATE AS create_date,"
			+ "ISSUES.CREATE_BY AS create_by_id,"
			+ "(SELECT USERS.FIRST_NAME FROM USERS where USERS.ID = ISSUES.CREATE_BY) AS create_by_first_name,"
			+ "(SELECT USERS.LAST_NAME FROM USERS where USERS.ID = ISSUES.CREATE_BY) AS create_by_last_name,"
			+ "ISSUES.MODIFY_DATE AS modity_date,"
			+ "ISSUES.MODIFIED_BY AS modify_by_id,"
			+ "(SELECT USERS.FIRST_NAME FROM USERS where USERS.ID = ISSUES.MODIFIED_BY) AS modify_by_first_name,"
			+ "(SELECT USERS.LAST_NAME FROM USERS where USERS.ID = ISSUES.MODIFIED_BY) AS modifiy_by_last_name,"
			+ "ISSUES.TYPE_ID AS type_id, TYPES.TP_NAME AS type_name,"
			+ "ISSUES.PRIORITY_ID AS priority_id, PRIORITIES.PR_NAME AS priority_name,"
			+ "ISSUES.STATUS_ID AS status_id, STATUSES.ST_NAME AS status_name,"
			+ "ISSUES.RESOLUTION_ID AS resolution_id, RESOLUTIONS.RES_NAME AS resolution_name,"
			+ "ISSUES.BUILD_ID AS build_id, BUILDS.BL_NAME AS build_name,"
			+ "BUILDS.PROJECT_ID AS project_id, PROJECTS.PROJECT_NAME AS project_name,"
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
			+ "ISSUES.TYPE_ID AS type_id, TYPES.TP_NAME AS type_name,"
			+ "ISSUES.PRIORITY_ID AS priority_id, PRIORITIES.PR_NAME AS priority_name,"
			+ "ISSUES.STATUS_ID AS status_id, STATUSES.ST_NAME AS status_name,"
			+ "ISSUES.RESOLUTION_ID AS resolution_id, RESOLUTIONS.RES_NAME AS resolution_name,"
			+ "ISSUES.BUILD_ID AS build_id, BUILDS.BL_NAME AS build_name,"
			+ "BUILDS.PROJECT_ID AS project_id, PROJECTS.PROJECT_NAME AS project_name,"
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

	@Override
	public Map<Long, Issue> getIssuesMap() throws SQLException {
		Map<Long,Issue> map = null;

		return map;
	}

}
