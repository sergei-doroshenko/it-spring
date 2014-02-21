package org.training.issuetracker.data.xml;

import java.io.IOException;
import java.util.Map;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.DAO.BuildDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class BuildImplXML implements BuildDAO {
	public static String resourceRealPath = Constants.getRealPath() + ConstantsXML.XML_RESOURCE_PATH;
	public static String schemaUrl = resourceRealPath + "build.xsd";
	private static String xmlUrl = resourceRealPath + "builds.xml";

	public BuildImplXML() { }

	@Override
	public Map<Long, Build> getBuildMap() throws DaoException {
		Map<Long, Build> builds = null;
		XMLValidator validator = new XMLValidator();
		try {
			validator.validateXML(schemaUrl, xmlUrl);

			XMLReader reader = XMLReaderFactory.createXMLReader();
			BuildHandler handler = new BuildHandler();
			reader.setContentHandler(handler);
			reader.parse(xmlUrl);
			builds = handler.getBuilds();

		} catch (ValidationException e) {
			throw new DaoException(e);
		} catch (SAXException e) {
			throw new DaoException(e);
		} catch (IOException e) {
			throw new DaoException(e);
		}

		return builds;
	}
}
