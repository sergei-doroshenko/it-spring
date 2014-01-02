package org.training.issuetracker.data.xml;

import java.util.HashMap;
import java.util.Map;

import org.training.issuetracker.domain.Project;
import org.training.issuetracker.domain.User;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProjectHandler extends DefaultHandler {
	private final int ID_ATTR_INDEX = 0;
	private final int NAME_ATTR_INDEX = 1;
	private String currEl;
	private Project currProject;
	private Map<Long, Project> projects = new HashMap<Long, Project>();
	
	public Map<Long, Project> getProjects() {
		return projects;
	}
	
	@Override
    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {
		if (qName.equals("p:project")) {
			currProject = new Project();
			long id = Long.parseLong(attributes.getValue(ID_ATTR_INDEX));
			String name = attributes.getValue(NAME_ATTR_INDEX);
			currProject.setId(id);
			currProject.setName(name);
		}
		
		if (!qName.equals("p:project") && !qName.equals("p:projects")) {
			currEl = qName;
		}
		
        System.out.println("start element    : " + qName + "--"+ currEl);
    }

	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("p:project")) {
			projects.put(currProject.getId(), currProject);
		}
        System.out.println("end element      : " + qName);
    }

	@Override
    public void characters(char ch[], int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		str = str.trim();
		
		if (null == currEl || str.isEmpty()) return;
		
		System.out.println(currEl + "=" + str);
		switch (currEl) {
			case "p:description" : 
				currProject.setDescription(str);
				break;
			case "p:build" :
				currProject.getBuilds().add(str);
				break;
			case "p:manager" :
				User manager = DataStorage.getInstance().getUser(str);
				currProject.setManager(manager);
				break;	
		}
	}
}
