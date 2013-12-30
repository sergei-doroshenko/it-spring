package org.training.issuetracker.domain.xml;

import java.util.HashMap;
import java.util.Map;

import org.training.issuetracker.domain.Priority;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PriorityHandler extends DefaultHandler {
	private String currEl;
	private Priority currPriority;
	private Map<Long, Priority> types = new HashMap<Long,Priority>();
	
	public Map<Long, Priority> getTypes() {
		return types;
	}
	
	@Override
    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {
		if (qName.equals("p:priority")) {
			currPriority = new Priority();
		}
		
		if (!qName.equals("p:priority") && !qName.equals("p:priorities")) {
			currEl = qName;
		}
		
        System.out.println("start element    : " + qName + "--"+ currEl);
    }

	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("p:type")) {
			types.put(currPriority.getId(), currPriority);
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
			case "p:id" : 
				currPriority.setId(Long.parseLong(str));
				break;
			case "p:name" :
				currPriority.setName(str);
				break;
		}
    }
}
