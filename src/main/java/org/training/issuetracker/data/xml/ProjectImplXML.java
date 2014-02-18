package org.training.issuetracker.data.xml;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.XMLValidator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class ProjectImplXML implements ProjectDAO {
	public static String resourceRealPath = Constants.getRealPath() + ConstantsXML.XML_RESOURCE_PATH;
	public static String schemaUrl = resourceRealPath + "project.xsd";
	private static String xmlUrl = resourceRealPath + "projects.xml";

	public ProjectImplXML() { }

	public Map<Long, Project> getProjectsMap() throws DaoException {
		Map<Long, Project> projects = null;
		XMLValidator validator = new XMLValidator();
		try {
			validator.validateXML(schemaUrl, xmlUrl);

			XMLReader reader = XMLReaderFactory.createXMLReader();
			ProjectHandler handler = new ProjectHandler();
			reader.setContentHandler(handler);
			reader.parse(xmlUrl);
			projects = handler.getProjects();

		} catch (ValidationException e) {
			throw new DaoException(e);
		} catch (SAXException e) {
			throw new DaoException(e);
		} catch (IOException e) {
			throw new DaoException(e);
		}

		return projects;
	}

	@Override
	public List<Project> getProjectsFormList() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project getProject(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Build> getProjectBuilds(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Build getBuild(long id) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long insertProject(Project project) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long updateProject(Project project) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteProject(long id) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long insertBuild(Build build) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long updateBuild(Build build) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long deleteBuild(long id) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Project> getProjectsList() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Build> getBuildsList() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
}
