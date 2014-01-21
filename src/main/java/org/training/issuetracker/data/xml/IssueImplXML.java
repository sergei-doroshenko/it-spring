package org.training.issuetracker.data.xml;

import java.io.IOException;
import java.util.Map;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class IssueImplXML implements IssueDAO {
	public static String resourceRealPath = Constants.RESOURCE_REAL_PATH + ConstantsXML.XML_RESOURCE_PATH;
	public static String schemaUrl = resourceRealPath + "issue.xsd";
	private static String xmlUrl = resourceRealPath + "issues.xml";

	public IssueImplXML() { }

	@Override
	public Map<Long, Issue> getIssue() throws DaoException {
		Map<Long, Issue> issues = null;
		XMLValidator validator = new XMLValidator();
		try {
			validator.validateXML(schemaUrl, xmlUrl);
			System.out.println ("xml is valid-------------------------------");
			XMLReader reader = XMLReaderFactory.createXMLReader();
			IssueHandler handler = new IssueHandler();
			reader.setContentHandler(handler);
			reader.parse(xmlUrl);
			issues = handler.getIssues();

		} catch (ValidationException e) {
			throw new DaoException(e);
		} catch (SAXException e) {
			throw new DaoException(e);
		} catch (IOException e) {
			throw new DaoException(e);
		}

		return issues;
	}

}
