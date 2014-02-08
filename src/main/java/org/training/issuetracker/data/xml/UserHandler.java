package org.training.issuetracker.data.xml;

import java.util.HashMap;
import java.util.Map;

import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.User;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandler extends DefaultHandler {
	private String currEl;
	private User currUser;
	private final Map<String, User> users = new HashMap<String, User>();

	public UserHandler() { }

	public Map<String, User> getUsersMap() {
		return users;
	}

	@Override
    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {
		if (qName.equals("p:user")) {
			currUser = new User();
		}

		if (!qName.equals("p:user") && !qName.equals("p:users")) {
			currEl = qName;
		}

    }

	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("p:user")) {
			users.put(currUser.getEmail(), currUser);
		}

    }

	@Override
    public void characters(char ch[], int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		str = str.trim();

		if (null == currEl || str.isEmpty()) return;

		switch (currEl) {
			case "p:id" :
				currUser.setId(Long.parseLong(str));
				break;
			case "p:firstname" :
				currUser.setFirstName(str);
				break;
			case "p:lastname" :
				currUser.setLastName(str);
				break;
			case "p:email" :
				currUser.setEmail(str);
				break;
			case "p:roleid" :
				
//				Role role = data.getRole(Long.parseLong(str));
//				currUser.setRole(role);
				break;
			case "p:password" :
				currUser.setPassword(str);
				break;
		}
    }
}
