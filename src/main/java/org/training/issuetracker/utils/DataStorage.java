package org.training.issuetracker.utils;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import org.training.issuetracker.domain.PersistentObj;
import org.training.issuetracker.domain.Priority;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.Type;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.PriorityDAO;
import org.training.issuetracker.domain.DAO.RoleDAO;
import org.training.issuetracker.domain.DAO.TypeDAO;
import org.training.issuetracker.exceptions.DaoException;

public class DataStorage {
	private static DataStorage instance;
	private Map<Long, Role> rolesMap;
	private Map<Long, Type> typesMap;
	private Map<Long, Priority> prioritiesMap;
	 
	private DataStorage() {
		RoleDAO roleDAO = DAOFactory.getDAO(RoleDAO.class);
		TypeDAO typeDAO = DAOFactory.getDAO(TypeDAO.class);
		PriorityDAO priorityDAO = DAOFactory.getDAO(PriorityDAO.class);
		try {
			this.rolesMap = roleDAO.getRolesMap();
			this.typesMap = typeDAO.getTypesMap();
			this.prioritiesMap = priorityDAO.getPriorityMap();
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

	public Map<Long, Type> getTypesMap() {
		return typesMap;
	}

	public Map<Long, Priority> getPrioritiesMap() {
		return prioritiesMap;
	}

	public <T> void printDataMap (PrintWriter out, Map<Long, T> map) {
		
		out.println("Data map:<br>");
		
		if (map != null) {
			for (Entry<Long, T> entry : map.entrySet()) {
				String str = entry.getKey() + "/" + ((PersistentObj) entry.getValue()).getName() + "; ";
				out.println(str);
			}
		} else {
			out.println("<b>Error</b>");
		}
		out.println("<br>");
	}
}
