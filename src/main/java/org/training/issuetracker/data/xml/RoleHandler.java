package org.training.issuetracker.data.xml;

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
	private final List<Role> roles = new ArrayList<Role>();
	private final Map<Long, Role> rols = new HashMap<Long, Role>();

	public Map<Long, Role> getRols() {
		return rols;
	}

	public List<Role> getRoles() {
		return roles;
	}

	@Override
	public void startDocument() throws SAXException {

    }

	@Override
    public void endDocument() throws SAXException {

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

    }

	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("p:role")) {
			roles.add(currRole);
			rols.put(currRole.getId(), currRole);
		}

    }

	@Override
    public void characters(char ch[], int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		str = str.trim();

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
