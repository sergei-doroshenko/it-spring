package org.training.issuetracker.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.data.db.PropImplDB.PropertyType;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.Attachment;
import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.Comment;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.AttachmentDAO;
import org.training.issuetracker.domain.DAO.CommentDAO;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.utils.ParameterParser;

public class EditIssueCommand extends AbstractWebCommand {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.command");

	public EditIssueCommand(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException, ServletException {

		ParameterParser parser = new ParameterParser(getRequest());

		User user = (User) getRequest().getSession().getAttribute(Constants.KEY_USER);

		if (user == null) {
			return;
		}

		try {
			String commandName = parser.getStringParameter(Constants.KEY_COMMAND);
			logger.debug("command = " + commandName);

			PropDAO propDAO = DAOFactory.getDAO(PropDAO.class);
			List<AbstractPersistentObj> statuses = propDAO.getPropList(PropertyType.STATUS);

			getRequest().setAttribute(Constants.STATUSES, statuses);

			List<AbstractPersistentObj> priorities = propDAO.getPropList(PropertyType.PRIORITY);
			getRequest().setAttribute(Constants.PRIORITIES, priorities);

			List<AbstractPersistentObj> types = propDAO.getPropList(PropertyType.TYPE);
			getRequest().setAttribute(Constants.TYPES, types);

			ProjectDAO projectDAO = DAOFactory.getDAO(ProjectDAO.class);
			List<Project> projects = projectDAO.getProjectsList();
			getRequest().setAttribute(Constants.PROJECTS, projects);

			UserDAO userDAO = DAOFactory.getDAO(UserDAO.class);
			List<User> assignee = userDAO.getUsersList();
			getRequest().setAttribute(Constants.ASSIGNEES, assignee);


			if (commandName.equals(Constants.COMMAND_EDIT_ISSUE)) {
				long id = parser.getLongParameter(Constants.KEY_ID);
				logger.debug("issue id = " + id);

				IssueDAO dao = DAOFactory.getDAO(IssueDAO.class);
				Issue issue = dao.getIssue(id);
				getRequest().setAttribute(Constants.ISSUE, issue);

				List<AbstractPersistentObj> resolutions = propDAO.getPropList(PropertyType.RESOLUTION);
				getRequest().setAttribute(Constants.RESOLUTIONS, resolutions);

				List<Build> builds = projectDAO.getProjectBuilds(issue.getProject().getId());
				getRequest().setAttribute(Constants.BUILDS, builds);

				CommentDAO comDAO = DAOFactory.getDAO(CommentDAO.class);
				List<Comment> comments = comDAO.getCommentsList(id);
				getRequest().setAttribute(Constants.COMMENTS, comments);

				AttachmentDAO attchDAO = DAOFactory.getDAO(AttachmentDAO.class);
				List<Attachment> attachments = attchDAO.getAttachmentsList(id);
				getRequest().setAttribute(Constants.ATTACHMENTS, attachments);

				jump(Constants.URL_EDIT_ISSUE);

			} else if (commandName.equals(Constants.COMMAND_SUBMIT_ISSUE)) {

				jump(Constants.URL_NEW_ISSUE);
			}

		} catch (NumberFormatException | ParameterNotFoundException e) {
			e.printStackTrace();
			jump(Constants.URL_ERROR);
		} catch (DaoException e) {

			e.printStackTrace();
			jump(Constants.URL_ERROR);
		}
	}



}
