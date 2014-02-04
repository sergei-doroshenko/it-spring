package org.training.issuetracker.data.xml;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.Priority;
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.domain.Resolution;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.Status;
import org.training.issuetracker.domain.Type;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.BuildDAO;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.domain.DAO.PriorityDAO;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.domain.DAO.ResolutionDAO;
import org.training.issuetracker.domain.DAO.RoleDAO;
import org.training.issuetracker.domain.DAO.StatusDAO;
import org.training.issuetracker.domain.DAO.TypeDAO;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;

/**
 * Dfta Storage
 * @author Sergei_Doroshenko
 *
 */
public class DataStorage {
	private static DataStorage instance;
	private Map<Long, Role> rolesMap;
	private Map<Long, Type> typesMap;
	private Map<Long, Priority> prioritiesMap;
	private Map<Long, Resolution> resolutionsMap;
	private Map<Long, Status> statusesMap;
	private Map<String, User> usersMap;
	private Map<Long, Project> projectsMap;
	private Map<Long, Issue> issuesMap;
	private Map<Long, Build> buildMap;

	/**
	 * Cjycnhucnjh
	 */
	private DataStorage() {
		RoleDAO roleDAO = DAOFactory.getDAO(RoleDAO.class);
		TypeDAO typeDAO = DAOFactory.getDAO(TypeDAO.class);
		PriorityDAO priorityDAO = DAOFactory.getDAO(PriorityDAO.class);
		ResolutionDAO resolutionDAO = DAOFactory.getDAO(ResolutionDAO.class);
		StatusDAO statusDAO = DAOFactory.getDAO(StatusDAO.class);
		BuildDAO buildDAO = DAOFactory.getDAO(BuildDAO.class);
		try {
			this.rolesMap = roleDAO.getRolesMap();
			this.typesMap = typeDAO.getTypesMap();
			this.prioritiesMap = priorityDAO.getPriorityMap();
			this.resolutionsMap = resolutionDAO.getResolutionMap();
			this.statusesMap = statusDAO.getStatusesMap();
			this.buildMap = buildDAO.getBuildMap();
		} catch (DaoException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Singletone
	 * @return DataStorage
	 */
	public static synchronized DataStorage getInstance() {
		if (instance == null) {
			instance = new DataStorage();
		}
		return instance;
	}

	/**
	 * @return
	 */
	public Map<Long, Role> getRolesMap() {
		return rolesMap;
	}

	/**
	 * @param id - ID
	 * @return
	 */
	public Role getRole (long id) {
		return rolesMap.get(id);
	}

	public Map<String, User> getUsersMap() {
		if (null == usersMap) {
			UserDAO userDAO = DAOFactory.getDAO(UserDAO.class);
			try {
				this.usersMap = userDAO.getUsersMap();
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return usersMap;
	}

	public User getUser (String login) {
		return getUsersMap().get(login);
	}

	public Map<Long, Type> getTypesMap() {
		return typesMap;
	}

	public Type getType (long id) {
		return typesMap.get(id);
	}

	public Map<Long, Priority> getPrioritiesMap() {
		return prioritiesMap;
	}

	public Priority getPriority (long id) {
		return prioritiesMap.get(id);
	}

	public Map<Long, Resolution> getResolutionsMap() {
		return resolutionsMap;
	}

	public Resolution getResolution (long id) {
		return resolutionsMap.get(id);
	}

	public Map<Long, Project> getProjectsMap() {
		if (null == projectsMap) {
			ProjectDAO projectDAO = DAOFactory.getDAO(ProjectDAO.class);
			try {
				this.projectsMap = projectDAO.getProjectsMap();
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		return projectsMap;
	}

	public Project getProject (long id) {
		return getProjectsMap().get(id);
	}

	public Map<Long, Status> getStatusesMap() {
		return statusesMap;
	}

	public Status getStatus (long id) {
		return statusesMap.get(id);
	}

	public Map<Long, Build> getBuildMap() {
		return buildMap;
	}

//	public Map<Long, Issue> getIssuesMap() {
//		if (null == issuesMap) {
//			IssueDAO issueDAO = DAOFactory.getDAO(IssueDAO.class);
//			try {
//				
//					this.issuesMap = issueDAO.getIssue();
//				
//			} catch (DaoException e) {
//				e.printStackTrace();
//			}
//		}
//		return issuesMap;
//	}
//
//	public Issue getIssue (long id) {
//		return getIssuesMap().get(id);
//	}

	public <T extends AbstractPersistentObj> void printDataMap (PrintWriter out, Map<?, T> map) {

		String str = "";
		String className = null;
		if (map != null) {
			for (Entry<?, T> entry : map.entrySet()) {
				if(null == className) {
					className = entry.getValue().getClass().getSimpleName();
					str += className + "<br>";
				}
				str +=  entry.getKey() + "//";

				if (className.equals("User") || className.equals("Project")
						|| className.equals("Issue")) {
					str += entry.getValue().toString() + "<br>";
				} else {
					str += entry.getValue().getName() + "; ";
				}
			}
		} else {
			out.println("<b>Error</b>");
		}
		out.println(str);
		out.println("<br>");
	}

	public Build getBuild(long id) {
		return getBuildsMap().get(id);
	}

	private Map<Long, Build> getBuildsMap() {
		return buildMap;
	}
}
