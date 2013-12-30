package org.training.issuetracker.model.impl;

import java.io.IOException;
import java.util.List;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.model.beans.Role;
import org.training.issuetracker.model.ifaces.IRoleDAO;
import org.training.issuetracker.utils.RoleHandler;
import org.training.issuetracker.utils.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class RoleImpXML implements IRoleDAO {
	
	public static String resourceRealPath;
	
	public String schemaUrl;
	private String xmlUrl;
	
	public RoleImpXML() {
	}
	
	public RoleImpXML(String schemaUrl, String xmlUrl) {
		super();
		this.schemaUrl = schemaUrl;
		this.xmlUrl = xmlUrl;
	}
	
	@Override
	public List<Role> getRoles() throws DaoException {
		String path = resourceRealPath + Constants.RESOURCE_PATHS;
		schemaUrl = path + "role.xsd";
		xmlUrl = path + "role.xml";
		
		List<Role> roles = null;
		XMLValidator validator = new XMLValidator();
		try {
			validator.validateXML(schemaUrl, xmlUrl);
			System.out.println ("xml is valid");
			XMLReader reader = XMLReaderFactory.createXMLReader();
			RoleHandler handler = new RoleHandler();
			reader.setContentHandler(handler);
			reader.parse(xmlUrl);
			roles = handler.getRoles();
			
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
