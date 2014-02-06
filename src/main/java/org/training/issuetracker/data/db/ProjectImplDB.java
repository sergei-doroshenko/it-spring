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

	private static final String SQL_SELECT_PROJECT =
			"SELECT PROJECTS.ID AS project_id,"
			+ "PROJECTS.PROJECT_NAME AS project_name "
			+ "FROM PROJECTS "
			+ "WHERE PROJECTS.ID = ?";

	private static final String SQL_SELECT_PROJECT_BUILDS =
			"SELECT BUILDS.ID AS build_id,"
			+ "BUILDS.BL_NAME AS build_name,"
			+ "BUILDS.PROJECT_ID AS project_id "
			+ "FROM BUILDS "
			+ "WHERE BUILDS.PROJECT_ID = ?";

	private static final String SQL_SELECT_BUILD =
			"SELECT BUILDS.ID AS build_id,"
			+ "BUILDS.BL_NAME AS build_name,"
			+ "BUILDS.PROJECT_ID AS project_id "
			+ "FROM BUILDS "
			+ "WHERE BUILDS.ID = ?";

	private static final int PROJECT_ID_INDEX = 1;

	private static final int BUILD_ID_INDEX = 1;

	@Override
	public List<Project> getProjectsList() throws DaoException {
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



}
