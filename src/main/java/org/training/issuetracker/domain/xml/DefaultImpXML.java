package org.training.issuetracker.domain.xml;

import java.io.IOException;
import java.util.Map;

import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.DAO.DefaultDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class DefaultImpXML<T> implements DefaultDAO {
	
	
	
	@Override
	public Map<Long, T> getResolutionMap() throws DaoException {
		Map<Long, T> roles = null;
		XMLValidator validator = new XMLValidator();
		try {
			validator.validateXML(schemaUrl, xmlUrl);
			System.out.println ("xml is valid");
			XMLReader reader = XMLReaderFactory.createXMLReader();
			PersistObjDefaultHandler<T> handler = new PersistObjDefaultHandler<T>(T.class, "p:roles", "p:role");
			reader.setContentHandler(handler);
			reader.parse(xmlUrl);
			roles = handler.getObjMap();
			
		} catch (ValidationException e) {
			throw new DaoException(e);	
		} catch (SAXException e) {
			throw new DaoException(e);
		} catch (IOException e) {
			throw new DaoException(e);
		}
		
		return roles;
	}

}
