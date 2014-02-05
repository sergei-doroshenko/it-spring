package org.training.issuetracker.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.JSONCreator;
import org.training.issuetracker.utils.ParameterInspector;
import org.training.issuetracker.utils.ParameterParser;

public class SaveIssueCommand extends AbstractWebCommand {
	Logger logger = Logger.getLogger("org.training.issuetracker.command");
	
	public SaveIssueCommand(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException, ServletException {
		getResponse().setContentType(MediaType.APPLICATION_JSON);
		PrintWriter out = getResponse().getWriter();
		ParameterParser parser = new ParameterParser(getRequest());
		
		try {
			long typeId = parser.getLongParameter(Constants.KEY_TYPE);
			long  priorityId = parser.getLongParameter(Constants.KEY_PRIORITY);
			long  statusId = parser.getLongParameter(Constants.KEY_STATUS);
			long  resolutionId = parser.getLongParameter(Constants.KEY_RESOLUTION);
			long  projectId = parser.getLongParameter(Constants.KEY_PROJECT);
			long  projectBuildId = parser.getLongParameter(Constants.KEY_PROJECT_BUILD);
			String  summary = parser.getStringParameter(Constants.KEY_SUMMARY);
			String  description = parser.getStringParameter(Constants.KEY_DESCRIPTION);
			
			//ParameterInspector.checkName(user.getLastName());
			Issue issue = new Issue();
			issue.setSummary(summary);
			issue.setDescription(description);
			
			IssueDAO dao = DAOFactory.getDAO(IssueDAO.class);
			long id = dao.insertIssue(issue);
			
			out.print(id);

		} catch (DaoException e) {
			e.printStackTrace();
			out.print(e.getMessage());
			getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);

		} catch (ParameterNotFoundException | NumberFormatException e) {
			out.print(e.getMessage());
			getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);

		} finally {
			out.flush();
			out.close();
		}
		
	}

}
