package org.training.issuetracker.data.xml;

import java.io.IOException;
import java.util.Map;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class UserImplXML implements UserDAO {
	public static String resourceRealPath = Constants.getRealPath() + ConstantsXML.XML_RESOURCE_PATH;
	public static String schemaUrl = resourceRealPath + "user.xsd";
	private static String xmlUrl = resourceRealPath + "users.xml";
	private Map<String, User> users = null;

	public UserImplXML() { }

	@Override
	public Map<String, User> getUsersMap() throws DaoException {
		if(null == users) {
			XMLValidator validator = new XMLValidator();
			try {
				validator.validateXML(schemaUrl, xmlUrl);

				XMLReader reader = XMLReaderFactory.createXMLReader();
				UserHandler handler = new UserHandler();
				reader.setContentHandler(handler);
				reader.parse(xmlUrl);
				users = handler.getUsersMap();

			} catch (ValidationException e) {
				throw new DaoException(e);
			} catch (SAXException e) {
				throw new DaoException(e);
			} catch (IOException e) {
				throw new DaoException(e);
			}
		}

		return users;
	}

	@Override
	public User getUser(String login, String password) throws DaoException {
		User user = getUsersMap().get(login);
		if (!(null == user)) {
			if (!user.getPassword().equals(password)) {
				user = null;
			}
		}

		return user;
	}
}
