package org.training.issuetracker.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.data.db.PropImplDB.PropertyType;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.Priority;
import org.training.issuetracker.domain.Resolution;
import org.training.issuetracker.domain.Status;
import org.training.issuetracker.domain.Type;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.utils.ParameterParser;

public class UpdateIssueCommand extends AbstractWebCommand {
	Logger logger = Logger.getLogger("org.training.issuetracker.command");

	public UpdateIssueCommand(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException, ServletException {
		getResponse().setContentType(MediaType.TEXT_PLAIN);
		PrintWriter out = getResponse().getWriter();
		ParameterParser parser = new ParameterParser(getRequest());

		User user = (User) getRequest().getSession().getAttribute(Constants.KEY_USER);

		if (user == null) {
			return;
		}

		try {
			long issueId = parser.getLongParameter(Constants.KEY_ID);
			long typeId = parser.getLongParameter(Constants.KEY_TYPE);
			long  priorityId = parser.getLongParameter(Constants.KEY_PRIORITY);
			long  statusId = parser.getLongParameter(Constants.KEY_STATUS);
			long  resolutionId = parser.getLongParameter(Constants.KEY_RESOLUTION);
			long  projectId = parser.getLongParameter(Constants.KEY_PROJECT);
			long  projectBuildId = parser.getLongParameter(Constants.KEY_PROJECT_BUILD);
			String  summary = parser.getStringParameter(Constants.KEY_SUMMARY);
			String  description = parser.getStringParameter(Constants.KEY_DESCRIPTION);
			long assigneeId = parser.getLongParameter(Constants.KEY_ASSIGNEE);

			//ParameterInspector.checkName(user.getLastName());
			Issue issue = new Issue();
			issue.setId(issueId);
			issue.setModifyBy(user);
			PropDAO propDAO = DAOFactory.getDAO(PropDAO.class);
			issue.setType((Type) propDAO.getProp(PropertyType.TYPE, typeId));
			issue.setPriority((Priority) propDAO.getProp(PropertyType.PRIORITY, priorityId));
			issue.setStatus((Status) propDAO.getProp(PropertyType.STATUS, statusId));
			issue.setResolution((Resolution) propDAO.getProp(PropertyType.RESOLUTION, resolutionId));

			ProjectDAO projectDAO = DAOFactory.getDAO(ProjectDAO.class);
			issue.setProject(projectDAO.getProject(projectId));
			issue.setBuild(projectDAO.getBuild(projectBuildId));

			issue.setSummary(summary);
			issue.setDescription(description);

			UserDAO userDAO = DAOFactory.getDAO(UserDAO.class);
			User assignee = userDAO.getUser(assigneeId);
			issue.setAssignee(assignee);

			IssueDAO dao = DAOFactory.getDAO(IssueDAO.class);
			long result = dao.updateIssue(issue);
			logger.debug("*********Update result = " + result);

			if (result == 1) {
				out.print("Main.do?id=1&command=issue");
			}



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
