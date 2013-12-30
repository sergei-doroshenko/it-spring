package org.training.issuetracker.domain.xml;

import java.io.IOException;
import java.util.Map;

import org.training.issuetracker.domain.Type;
import org.training.issuetracker.domain.DAO.TypeDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class TypeImplXML implements TypeDAO {
	public static String resourceRealPath = ConstantsXML.RESOURCE_REAL_PATH + ConstantsXML.XML_RESOURCE_PATH;
	public static String schemaUrl = resourceRealPath + "type.xsd";
	private static String xmlUrl = resourceRealPath + "type.xml";
	
	public TypeImplXML() { }

	@Override
	public Map<Long, Type> getTypesMap() throws DaoException {
		Map<Long, Type> types = null;
		XMLValidator validator = new XMLValidator();
		try {
			validator.validateXML(schemaUrl, xmlUrl);
			System.out.println ("xml is valid-------------------------------");
			XMLReader reader = XMLReaderFactory.createXMLReader();
			PersistObjDefaultHandler<Type> handler = new PersistObjDefaultHandler<Type>(Type.class, "p:types", "p:type");
			reader.setContentHandler(handler);
			reader.parse(xmlUrl);
			types = handler.getObjMap();
			
		} catch (ValidationException e) {
			throw new DaoException(e);	
		} catch (SAXException e) {
			throw new DaoException(e);
		} catch (IOException e) {
			throw new DaoException(e);
		}
		
		return types;
	}
	
//	@Override
//	public Map<Long, Type> getTypesMap() throws DaoException {
//		Map<Long, Type> types = null;
//		XMLValidator validator = new XMLValidator();
//		try {
//			validator.validateXML(schemaUrl, xmlUrl);
//			System.out.println ("xml is valid");
//			XMLReader reader = XMLReaderFactory.createXMLReader();
//			TypeHandler handler = new TypeHandler();
//			reader.setContentHandler(handler);
//			reader.parse(xmlUrl);
//			types = handler.getTypes();
//			
//		} catch (ValidationException e) {
//			throw new DaoException(e);	
//		} catch (SAXException e) {
//			throw new DaoException(e);
//		} catch (IOException e) {
//			throw new DaoException(e);
//		}
//		
//		return types;
//	}
	
}
