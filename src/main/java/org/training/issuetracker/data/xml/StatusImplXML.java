package org.training.issuetracker.data.xml;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.data.db.PropImplDB.PropertyType;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.Status;
import org.training.issuetracker.domain.DAO.StatusDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class StatusImplXML implements StatusDAO {
	public static String resourceRealPath = Constants.getRealPath() + ConstantsXML.XML_RESOURCE_PATH;
	public static String schemaUrl = resourceRealPath + "status.xsd";
	private static String xmlUrl = resourceRealPath + "statuses.xml";

	public StatusImplXML() { }

	public Map<Long, Status> getStatusesMap() throws DaoException {
		Map<Long, Status> statuses = null;
		XMLValidator validator = new XMLValidator();
		try {
			validator.validateXML(schemaUrl, xmlUrl);

			XMLReader reader = XMLReaderFactory.createXMLReader();
			PersistObjDefaultHandler<Status> handler =
					new PersistObjDefaultHandler<Status>(Status.class, "p:statuses", "p:status");
			reader.setContentHandler(handler);
			reader.parse(xmlUrl);
			statuses = handler.getObjMap();

		} catch (ValidationException e) {
			throw new DaoException(e);
		} catch (SAXException e) {
			throw new DaoException(e);
		} catch (IOException e) {
			throw new DaoException(e);
		}

		return statuses;
	}

	@Override
	public List<AbstractPersistentObj> getPropList(PropertyType prop)
			throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
}
