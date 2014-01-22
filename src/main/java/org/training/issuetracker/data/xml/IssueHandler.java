package org.training.issuetracker.data.xml;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class IssueHandler extends DefaultHandler {
	private final int ID_ATTR_INDEX = 0;
	private final int NAME_ATTR_INDEX = 1;
	private String currEl;
	private Issue currIssue;
	private final Map<Long, Issue> issues = new HashMap<Long, Issue>();

	public Map<Long, Issue> getIssues() {
		return issues;
	}

	@Override
    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {
		if (qName.equals("p:issue")) {
			currIssue = new Issue();
			long id = Long.parseLong(attributes.getValue(ID_ATTR_INDEX));
			String name = attributes.getValue(NAME_ATTR_INDEX);
			currIssue.setId(id);
			currIssue.setName(name);
		}

		if (!qName.equals("p:issue") && !qName.equals("p:issues")) {
			currEl = qName;
		}
    }

	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equals("p:issue")) {
			issues.put(currIssue.getId(), currIssue);
		}

    }

	@Override
    public void characters(char ch[], int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		str = str.trim();

		if (null == currEl || str.isEmpty()) return;
		User user = null;
		DataStorage data = DataStorage.getInstance();

		switch (currEl) {
			case "p:createdate" :
				currIssue.setCreateDate(Date.valueOf(str));
				break;
			case "p:createby" :
				user = data.getUser(str);
				currIssue.setCreateBy(user);
				break;
			case "p:modifydate" :
				currIssue.setModifyDate(Date.valueOf(str));
				break;
			case "p:modifyby" :
				user = data.getUser(str);
				currIssue.setModifyBy(user);
				break;
			case "p:summary" :
				currIssue.setSummary(str);
				break;
			case "p:description" :
				currIssue.setDescription(str);
				break;
			case "p:status" :
				currIssue.setStatus(data.getStatus(Long.parseLong(str)));
				break;
			case "p:resolution" :
				currIssue.setResolution(data.getResolution(Long.parseLong(str)));
				break;
			case "p:type" :
				currIssue.setType(data.getType(Long.parseLong(str)));
				break;
			case "p:priority" :
				currIssue.setPriority(data.getPriority(Long.parseLong(str)));
				break;
			case "p:project" :
				currIssue.setProject(data.getProject(Long.parseLong(str)));
				break;
			case "p:assignee" :
				user = data.getUser(str);
				currIssue.setAssignee(user);
				break;
		}
    }
}
