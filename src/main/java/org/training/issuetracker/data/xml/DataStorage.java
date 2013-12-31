package org.training.issuetracker.data.xml;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.Priority;
import org.training.issuetracker.domain.Resolution;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.Type;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.PriorityDAO;
import org.training.issuetracker.domain.DAO.ResolutionDAO;
import org.training.issuetracker.domain.DAO.RoleDAO;
import org.training.issuetracker.domain.DAO.TypeDAO;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;

public class DataStorage {
	private static DataStorage instance;
	private Map<Long, Role> rolesMap;
	private Map<Long, Type> typesMap;
	private Map<Long, Priority> prioritiesMap;
	private Map<Long, Resolution> resolutionsMap;
	private Map<String, User> usersMap;
	 
	private DataStorage() {
		RoleDAO roleDAO = DAOFactory.getDAO(RoleDAO.class);
		TypeDAO typeDAO = DAOFactory.getDAO(TypeDAO.class);
		PriorityDAO priorityDAO = DAOFactory.getDAO(PriorityDAO.class);
		ResolutionDAO resolutionDAO = DAOFactory.getDAO(ResolutionDAO.class);
		try {
			this.rolesMap = roleDAO.getRolesMap();
			this.typesMap = typeDAO.getTypesMap();
			this.prioritiesMap = priorityDAO.getPriorityMap();
			this.resolutionsMap = resolutionDAO.getResolutionMap();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}
 
	public static synchronized DataStorage getInstance() {
		if (instance == null) {
			instance = new DataStorage();
		}
		return instance;
	}

	public Map<Long, Role> getRolesMap() {
		return rolesMap;
	}
	
	public Role getRole (long id) {
		return rolesMap.get(id);
	}
	
	public Map<String, User> getUsersMap() {
		UserDAO userDAO = DAOFactory.getDAO(UserDAO.class);
		try {
			this.usersMap = userDAO.getUsersMap();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersMap;
	}

	public Map<Long, Type> getTypesMap() {
		return typesMap;
	}

	public Map<Long, Priority> getPrioritiesMap() {
		return prioritiesMap;
	}
	
	public Map<Long, Resolution> getResolutionsMap() {
		return resolutionsMap;
	}

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
				
				if (className.equals("User")) {
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
}
