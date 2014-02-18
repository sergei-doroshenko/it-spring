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
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.ConnectionProvider;

public class ProjectImplDB implements ProjectDAO {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.data");
	private Connection connection;

	private static final String SQL_SELECT_FORM_LIST =
			"SELECT PROJECTS.ID AS project_id,"
			+ "PROJECTS.PROJECT_NAME AS project_name "
			+ "FROM PROJECTS";

	private static final String SQL_SELECT_PROJECTS_LIST =
			"SELECT PROJECTS.ID AS project_id,"
			+ "PROJECTS.PROJECT_NAME AS project_name,"
			+ "PROJECTS.DESCRIPTION AS project_description,"
			+ "PROJECTS.MANAGER AS project_manager_id,"
			+ "USERS.FIRST_NAME AS project_manager_first_name,"
			+ "USERS.LAST_NAME AS project_manager_last_name,"
			+ "USERS.EMAIL AS project_manager_email, USERS.PASSWORD AS project_manager_password,"
			+ "ROLES.ID AS project_manager_role_id, ROLES.RL_NAME AS project_manager_role_name "
			+ "FROM PROJECTS "
			+ "LEFT JOIN USERS ON PROJECTS.MANAGER = USERS.ID "
			+ "LEFT JOIN ROLES ON USERS.ROLE_ID = ROLES.ID";

	private static final String SQL_SELECT_PROJECT =
			"SELECT PROJECTS.ID AS project_id,"
			+ "PROJECTS.PROJECT_NAME AS project_name "
			+ "FROM PROJECTS "
			+ "WHERE PROJECTS.ID = ?";

	private static final String SQL_INSERT_PROJECT =
			"INSERT INTO PROJECTS (PROJECT_NAME, DESCRIPTION, MANAGER) "
			+ "VALUES (?, ?, ?)";

	private static final int INSERT_PROJECT_NAME_IND = 1;

	private static final int INSERT_PROJECT_DESCRIPTION_IND = 2;

	private static final int INSERT_PROJECT_MANAGER_IND = 3;

	private static final String SQL_UPDATE_PROJECT =
			"UPDATE PROJECTS SET "
			+ "PROJECTS.PROJECT_NAME = ?,"
			+ "PROJECTS.DESCRIPTION = ?,"
			+ "PROJECTS.MANAGER = ? "
			+ "WHERE PROJECTS.ID = ?";

	private static final int UPDATE_PROJECT_NAME_IND = 1;

	private static final int UPDATE_PROJECT_DESCRIPTION_IND = 2;

	private static final int UPDATE_PROJECT_MANAGER_IND = 3;

	private static final int UPDATE_PROJECT_ID_IND = 4;

	private static final String SQL_DELETE_PROJECT =
			"DELETE FROM PROJECTS WHERE PROJECTS.ID = ?";

	private static final int DELETE_PROJECT_ID_IND = 1;

	private static final String SQL_SELECT_PROJECT_BUILDS =
			"SELECT BUILDS.ID AS build_id,"
			+ "BUILDS.BL_NAME AS build_name,"
			+ "BUILDS.PROJECT_ID AS project_id "
			+ "FROM BUILDS "
			+ "WHERE BUILDS.PROJECT_ID = ?";

	private static final String SQL_SELECT_BUILDS_LIST =
			"SELECT BUILDS.ID AS build_id,"
			+ "BUILDS.BL_NAME AS build_name,"
			+ "BUILDS.PROJECT_ID AS project_id "
			+ "FROM BUILDS";

	private static final String SQL_SELECT_BUILD =
			"SELECT BUILDS.ID AS build_id,"
			+ "BUILDS.BL_NAME AS build_name,"
			+ "BUILDS.PROJECT_ID AS project_id "
			+ "FROM BUILDS "
			+ "WHERE BUILDS.ID = ?";

	private static final int PROJECT_ID_INDEX = 1;

	private static final int BUILD_ID_INDEX = 1;

	private static final String SQL_INSERT_BUILD = "INSERT INTO BUILDS (BL_NAME, PROJECT_ID) VALUES (?, ?)";

	private static final int INSERT_BUILD_NAME_IND = 1;

	private static final int INSERT_BUILD_PROJECT_ID_IND = 2;

	private static final String SQL_UPDATE_BUILD =
			"UPDATE BUILDS SET "
			+ "BUILDS.BL_NAME = ?,"
			+ "BUILDS.PROJECT_ID = ? "
			+ "WHERE BUILDS.ID = ?";

	private static final int UPDATE_BUILD_NAME_IND = 1;

	private static final int UPDATE_BUILD_PROJECT_ID_IND = 2;

	private static final int UPDATE_BUILD_ID_IND = 3;

	private static final String SQL_DELETE_BUILD = "DELETE FROM BUILDS WHERE BUILDS.ID = ?";

	private static final int DELETE_BUILD_ID_IND = 1;

	@Override
	public List<Project> getProjectsFormList() throws DaoException {
		List<Project> list = new ArrayList<Project>();
		Statement select = null;
		ResultSet rs = null;

		try {
			connection = ConnectionProvider.getConnection();
			select = connection.createStatement();

			rs = select.executeQuery(SQL_SELECT_FORM_LIST);

			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getLong("project_id"));
				project.setName(rs.getString("project_name"));
				list.add(project);
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
	public List<Project> getProjectsList() throws DaoException {
		List<Project> list = new ArrayList<Project>();
		Statement select = null;
		ResultSet rs = null;

		try {
			connection = ConnectionProvider.getConnection();
			select = connection.createStatement();

			rs = select.executeQuery(SQL_SELECT_PROJECTS_LIST);

			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getLong("project_id"));
				project.setName(rs.getString("project_name"));
				project.setDescription(rs.getString("project_description"));
				User manager = new User();
				manager.setId(rs.getLong("project_manager_id"));
				manager.setFirstName(rs.getString("project_manager_first_name"));
				manager.setLastName(rs.getString("project_manager_last_name"));
				manager.setEmail(rs.getString("project_manager_email"));
				manager.setPassword(rs.getString("project_manager_password"));
				Role role = new Role(rs.getLong("project_manager_role_id"), rs.getString("project_manager_role_name"));
				manager.setRole(role);
				project.setManager(manager);
				list.add(project);
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
	public List<Build> getProjectBuilds(long id) throws DaoException {
		List<Build> list = new ArrayList<Build>();
		PreparedStatement select = null;
		ResultSet rs = null;
		try {
			connection = ConnectionProvider.getConnection();
			select = connection.prepareStatement(SQL_SELECT_PROJECT_BUILDS);
			select.setLong(PROJECT_ID_INDEX, id);
			rs = select.executeQuery();
			while (rs.next()) {
				Build build = new Build(rs.getLong("build_id"), rs.getString("build_name"), rs.getLong("project_id"));
				list.add(build);
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
	public Project getProject(long id) throws DaoException {
		Project project = new Project();
		PreparedStatement select = null;
		ResultSet rs = null;
		try {
			connection = ConnectionProvider.getConnection();
			select = connection.prepareStatement(SQL_SELECT_PROJECT);
			select.setLong(PROJECT_ID_INDEX, id);
			rs = select.executeQuery();

			while (rs.next()) {
				project.setId(rs.getLong("project_id"));
				project.setName(rs.getString("project_name"));
			}
			return project;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(select);
		}
	}

	@Override
	public Build getBuild(long id) throws DaoException {
		Build build = new Build();
		PreparedStatement select = null;
		ResultSet rs = null;
		try {
			connection = ConnectionProvider.getConnection();
			select = connection.prepareStatement(SQL_SELECT_BUILD);
			select.setLong(BUILD_ID_INDEX, id);
			rs = select.executeQuery();

			while (rs.next()) {
				build.setId(rs.getLong("build_id"));
				build.setName(rs.getString("build_name"));
				build.setProjectId(rs.getLong("project_id"));
			}
			return build;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(Constants.ERROR_SOURCE, e);
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(select);
		}
	}


	@Override
	public long insertProject(Project project) throws DaoException {
		PreparedStatement st = null;

		try {
			connection = ConnectionProvider.getConnection();
			st = connection.prepareStatement(SQL_INSERT_PROJECT);
			st.setString(INSERT_PROJECT_NAME_IND, project.getName());
			st.setString(INSERT_PROJECT_DESCRIPTION_IND, project.getDescription());
			st.setLong(INSERT_PROJECT_MANAGER_IND, project.getManager().getId());
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
	public long updateProject(Project project) throws DaoException {
		PreparedStatement st = null;

		try {
			connection = ConnectionProvider.getConnection();
			st = connection.prepareStatement(SQL_UPDATE_PROJECT);
			st.setString(UPDATE_PROJECT_NAME_IND, project.getName());
			st.setString(UPDATE_PROJECT_DESCRIPTION_IND, project.getDescription());
			st.setLong(UPDATE_PROJECT_MANAGER_IND, project.getManager().getId());
			st.setLong(UPDATE_PROJECT_ID_IND, project.getId());

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
	public long deleteProject(long id) throws DaoException {
		PreparedStatement st = null;

		try {
			connection = ConnectionProvider.getConnection();
			st = connection.prepareStatement(SQL_DELETE_PROJECT);
			st.setLong(DELETE_PROJECT_ID_IND, id);

			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e.getLocalizedMessage());
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(st);
		}
	}

	@Override
	public long insertBuild(Build build) throws DaoException {
		PreparedStatement st = null;

		try {
			connection = ConnectionProvider.getConnection();
			st = connection.prepareStatement(SQL_INSERT_BUILD);
			st.setString(INSERT_BUILD_NAME_IND, build.getName());
			st.setLong(INSERT_BUILD_PROJECT_ID_IND, build.getProjectId());

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
	public long updateBuild(Build build) throws DaoException {
		PreparedStatement st = null;

		try {
			connection = ConnectionProvider.getConnection();
			st = connection.prepareStatement(SQL_UPDATE_BUILD);
			st.setString(UPDATE_BUILD_NAME_IND, build.getName());
			st.setLong(UPDATE_BUILD_PROJECT_ID_IND, build.getProjectId());
			st.setLong(UPDATE_BUILD_ID_IND, build.getId());

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
	public long deleteBuild(long id) throws DaoException {
		PreparedStatement st = null;

		try {
			connection = ConnectionProvider.getConnection();
			st = connection.prepareStatement(SQL_DELETE_BUILD);
			st.setLong(DELETE_BUILD_ID_IND, id);

			return st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e.getLocalizedMessage());
		} finally {
			ConnectionProvider.closeConnection(connection);
			ConnectionProvider.closePrepStatemnts(st);
		}
	}

	@Override
	public List<Build> getBuildsList() throws DaoException {
		List<Build> list = new ArrayList<Build>();
		Statement select = null;
		ResultSet rs = null;

		try {
			connection = ConnectionProvider.getConnection();
			select = connection.createStatement();

			rs = select.executeQuery(SQL_SELECT_BUILDS_LIST);

			while (rs.next()) {
				Build build = new Build();
				build.setId(rs.getLong("build_id"));
				build.setName(rs.getString("build_name"));
				build.setProjectId(rs.getLong("project_id"));

				list.add(build);
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
