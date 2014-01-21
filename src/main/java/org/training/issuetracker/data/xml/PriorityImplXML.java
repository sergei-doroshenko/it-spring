package org.training.issuetracker.data.xml;

import java.io.IOException;
import java.util.Map;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Priority;
import org.training.issuetracker.domain.DAO.PriorityDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class PriorityImplXML implements PriorityDAO {
	public static String resourceRealPath = Constants.RESOURCE_REAL_PATH + ConstantsXML.XML_RESOURCE_PATH;
	public static String schemaUrl = resourceRealPath + "priority.xsd";
	private static String xmlUrl = resourceRealPath + "priorities.xml";

	@Override
	public Map<Long, Priority> getPriorityMap() throws DaoException {
		Map<Long, Priority> priorities = null;
		XMLValidator validator = new XMLValidator();
		try {
			validator.validateXML(schemaUrl, xmlUrl);
			System.out.println ("xml is valid-------------------------------");
			XMLReader reader = XMLReaderFactory.createXMLReader();
			PersistObjDefaultHandler<Priority> handler = new PersistObjDefaultHandler<Priority>(Priority.class, "p:priorities", "p:priority");
			reader.setContentHandler(handler);
			reader.parse(xmlUrl);
			priorities = handler.getObjMap();

		} catch (ValidationException e) {
			throw new DaoException(e);
		} catch (SAXException e) {
			throw new DaoException(e);
		} catch (IOException e) {
			throw new DaoException(e);
		}

		return priorities;
	}

}
