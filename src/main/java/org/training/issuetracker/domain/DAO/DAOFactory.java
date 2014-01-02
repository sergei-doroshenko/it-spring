package org.training.issuetracker.domain.DAO;

import java.util.HashMap;
import java.util.Map;

import org.training.issuetracker.data.xml.PriorityImplXML;
import org.training.issuetracker.data.xml.ProjectImplXML;
import org.training.issuetracker.data.xml.ResolutionImplXML;
import org.training.issuetracker.data.xml.RoleImpXML;
import org.training.issuetracker.data.xml.StatusImplXML;
import org.training.issuetracker.data.xml.TypeImplXML;
import org.training.issuetracker.data.xml.UserImplXML;

public class DAOFactory {
	private static Map<Class<?>, Object> factories = new HashMap<Class<?>, Object>();
	
	public DAOFactory() {}
	
	static{
		factories.put(RoleDAO.class, new RoleImpXML());
		factories.put(TypeDAO.class, new TypeImplXML());
		factories.put(PriorityDAO.class, new PriorityImplXML());
		factories.put(ResolutionDAO.class, new ResolutionImplXML());
		factories.put(StatusDAO.class, new StatusImplXML());
		factories.put(UserDAO.class, new UserImplXML());
		factories.put(ProjectDAO.class, new ProjectImplXML());
		factories.put(IssueDAO.class, new IssueImplXML());
//		factories.put(IPlayDAO.class, new PlayImplDB());
//		factories.put(IPlayDAO.class, new PlayImplXML());
	}
	
	public <T> void putDAO(Class<T> type, T instance) {
		if (type == null){
			throw new NullPointerException("Type is null");
		}
		factories.put(type, instance);
	}
	
	public static <T> T getDAO(Class<T> type) {
		
		return type.cast(factories.get(type));
	}
	
	
	
}
