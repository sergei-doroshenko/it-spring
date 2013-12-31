package org.training.issuetracker.domain.xml;

import java.io.IOException;
import java.util.Map;

import org.training.issuetracker.domain.Resolution;
import org.training.issuetracker.domain.DAO.ResolutionDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ResolutionImplXML implements ResolutionDAO {
	
	public static String resourceRealPath = ConstantsXML.RESOURCE_REAL_PATH + ConstantsXML.XML_RESOURCE_PATH;
	public static String schemaUrl = resourceRealPath + "role.xsd";
	private static String xmlUrl = resourceRealPath + "role.xml";
	
	@Override
	public Map<Long, Resolution> getResolutionMap() throws DaoException {
		Map<Long, Resolution> resolutions = null;
		XMLValidator validator = new XMLValidator();
		try {
			validator.validateXML(schemaUrl, xmlUrl);
			System.out.println ("xml is valid");
			XMLReader reader = XMLReaderFactory.createXMLReader();
			PersistObjDefaultHandler<Resolution> handler = new PersistObjDefaultHandler<Resolution>(Resolution.class, "p:roles", "p:role");
			reader.setContentHandler(handler);
			reader.parse(xmlUrl);
			resolutions = handler.getObjMap();
			
		} catch (ValidationException e) {
			throw new DaoException(e);	
		} catch (SAXException e) {
			throw new DaoException(e);
		} catch (IOException e) {
			throw new DaoException(e);
		}
		return resolutions;
	}
	
}
