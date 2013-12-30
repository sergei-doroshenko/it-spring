package org.training.issuetracker.utils;

import java.util.ArrayList;
import java.util.List;

import org.training.issuetracker.model.beans.Role;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RoleHandler extends DefaultHandler {
	
	private String currEl;
	private Role currRole;
	private List<Role> roles = new ArrayList<Role>();
	
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
				currRole.setId(Integer.parseInt(str));
				break;
			case "p:name" :
				currRole.setName(str);
				break;
		}
    }

}
