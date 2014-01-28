package org.training.issuetracker.data.xml;

import java.util.HashMap;
import java.util.Map;

import org.training.issuetracker.domain.Build;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BuildHandler extends DefaultHandler {
	private final int ID_ATTR_INDEX = 0;
	private final int NAME_ATTR_INDEX = 1;
	private String currEl;
	private Build currBuild;
	private final Map<Long, Build> builds = new HashMap<Long, Build>();

	public Map<Long, Build> getBuilds() {
		return builds;
	}

	@Override
    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {
		if (qName.equals("p:build")) {
			currBuild = new Build();
			long id = Long.parseLong(attributes.getValue(ID_ATTR_INDEX));
			String name = attributes.getValue(NAME_ATTR_INDEX);
			currBuild.setId(id);
			currBuild.setName(name);
		}

		if (!qName.equals("p:project") && !qName.equals("p:projects")) {
			currEl = qName;
		}

    }

	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("p:build")) {
			builds.put(currBuild.getId(), currBuild);
		}

    }

	@Override
    public void characters(char ch[], int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		str = str.trim();

		if (null == currEl || str.isEmpty()) return;

		switch (currEl) {
			case "p:projectid" :
				currBuild.setProjectId(Long.parseLong(str));
				break;
		}
	}

}
