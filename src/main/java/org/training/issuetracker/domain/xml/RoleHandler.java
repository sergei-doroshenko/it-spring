package org.training.issuetracker.domain.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.training.issuetracker.domain.Role;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RoleHandler extends DefaultHandler {
	private String currEl;
	private Role currRole;
	private List<Role> roles = new ArrayList<Role>();
	private Map<Long, Role> rols = new HashMap<Long, Role>();
	
	public Map<Long, Role> getRols() {
		return rols;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	@Override
	public void startDocument() throws SAXException {
        System.out.println("start document   : ");
    }
	
	@Override
    public void endDocument() throws SAXException {
        System.out.println("end document     : ");
    }
	
	@Override
    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {
		if (qName.equals("p:role")) {
			currRole = new Role();
		}
		
		if (!qName.equals("p:role") && !qName.equals("p:roles")) {
			currEl = qName;
		}
		
        System.out.println("start element    : " + qName + "--"+ currEl);
    }

	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("p:role")) {
			roles.add(currRole);
			rols.put(currRole.getId(), currRole);
		}
        System.out.println("end element      : " + qName);
    }

	@Override
    public void characters(char ch[], int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		str = str.trim();
		//str = str.replace("\n", "").replace("\r", "").replace(" ", "").trim();
        //System.out.println("ch =" + str + "=" + str.length());
		if (null == currEl || str.isEmpty()) return;
		
		System.out.println(currEl + "=" + str);
		switch (currEl) {
			case "p:id" : 
				currRole.setId(Long.parseLong(str));
				break;
			case "p:name" :
				currRole.setName(str);
				break;
		}
    }

}
