package org.training.issuetracker.data.xml;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class IssueImplXML implements IssueDAO {
	public static String resourceRealPath = Constants.getRealPath() + ConstantsXML.XML_RESOURCE_PATH;
	public static String schemaUrl = resourceRealPath + "issue.xsd";
	private static String xmlUrl = resourceRealPath + "issues.xml";


	public IssueImplXML() { }

	public Map<Long, Issue> getIssue() throws DaoException {
		Map<Long, Issue> issues = null;
		XMLValidator validator = new XMLValidator();
		try {
			validator.validateXML(schemaUrl, xmlUrl);
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

	
	public Map<Long, Issue> getIssuesMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Issue> getIssueList() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Issue> getIssueList(User user) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Issue getIssueById(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isId(long id) throws DaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertIssue(Issue issue) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long updateIssue(Issue issue) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteIssue(long id) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

}
