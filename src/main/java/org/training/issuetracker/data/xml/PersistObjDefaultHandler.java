package org.training.issuetracker.data.xml;

import java.util.HashMap;
import java.util.Map;

import org.training.issuetracker.domain.AbstractPersistentObj;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PersistObjDefaultHandler <T extends AbstractPersistentObj> extends DefaultHandler {
	private String currEl;
	private String rootTag;
	private String itemTag;
	private T currObj;
	
	private Class<T> cl;
    private Map<Long, T> objMap = new HashMap<Long,T>();
    
    public PersistObjDefaultHandler(Class<T> cl, String rootTag, String itemTag) {
            super();
            this.cl = cl;
            this.rootTag = rootTag;
            this.itemTag = itemTag;
    }

	public Map<Long, T> getObjMap() {
		return objMap;
	}
	
	@Override
    public void startElement(String uri, String localName,
        String qName, Attributes attributes) throws SAXException {
		if (qName.equals(itemTag)) {
			try {
				currObj = cl.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		if (!qName.equals(itemTag) && !qName.equals(rootTag)) {
			currEl = qName;
		}
		
        System.out.println("start element    : " + qName + "--"+ currEl);
    }

	@Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals(itemTag)) {
			objMap.put(currObj.getId(), currObj);
		}
        System.out.println("end-element      : " + qName);
    }

	@Override
    public void characters(char ch[], int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		str = str.trim();
		
		if (null == currEl || str.isEmpty()) return;
		
		System.out.println(currEl + "=" + str);
		switch (currEl) {
			case "p:id" : 
				currObj.setId(Long.parseLong(str));
				break;
			case "p:name" :
				currObj.setName(str);
				break;
		}
    }
	
}
