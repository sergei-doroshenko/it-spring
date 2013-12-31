package org.training.issuetracker.domain.xml;

import java.util.HashMap;
import java.util.Map;

import org.training.issuetracker.domain.Type;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TypeHandler extends DefaultHandler {
	private String currEl;
	private Type currType;
	private Map<Long, Type> types = new HashMap<Long,Type>();
	
	public Map<Long, Type> getTypes() {
		return types;
	}
	
	@Override
    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {
		if (qName.equals("p:type")) {
			currType = new Type();
		}
		
		if (!qName.equals("p:type") && !qName.equals("p:types")) {
			currEl = qName;
		}
		
        System.out.println("start element    : " + qName + "--"+ currEl);
    }

	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("p:type")) {
			types.put(currType.getId(), currType);
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
				currType.setId(Long.parseLong(str));
				break;
			case "p:name" :
				currType.setName(str);
				break;
		}
    }
}
