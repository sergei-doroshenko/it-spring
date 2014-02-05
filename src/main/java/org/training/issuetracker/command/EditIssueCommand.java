package org.training.issuetracker.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Attachment;
import org.training.issuetracker.domain.Comment;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.AttachmentDAO;
import org.training.issuetracker.domain.DAO.CommentDAO;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.IssueDAO;
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

		HttpSession session = getRequest().getSession();

		User user = (User) getRequest().getSession().getAttribute(Constants.KEY_USER);
		logger.debug("User = " + user);

		if (user == null) {
			return;
		}

		try {
			String commandName = parser.getStringParameter(Constants.KEY_COMMAND);
			logger.debug("command = " + commandName);

			if (commandName.equals(Constants.COMMAND_EDIT_ISSUE)) {
				long id = parser.getLongParameter(Constants.KEY_ID);
				logger.debug("issue id = " + id);

				IssueDAO dao = DAOFactory.getDAO(IssueDAO.class);
				Issue issue = dao.getIssue(id);
				getRequest().setAttribute(Constants.ISSUE, issue);

				CommentDAO comDAO = DAOFactory.getDAO(CommentDAO.class);
				List<Comment> comments = comDAO.getCommentsList(id);
				getRequest().setAttribute(Constants.COMMENTS, comments);

				AttachmentDAO attchDAO = DAOFactory.getDAO(AttachmentDAO.class);
				List<Attachment> attachments = attchDAO.getAttachmentsList(id);
				getRequest().setAttribute(Constants.ATTACHMENTS, attachments);



			} else if (commandName.equals(Constants.COMMAND_SUBMIT_ISSUE)) {

			}

			jump(Constants.URL_EDIT_ISSUE);

//			long id = parser.getLongParameter(Constants.KEY_ID);
//			DataStorage data = DataStorage.getInstance();
//			Issue issue = data.getIssue(id);
//
//			HttpSession session = getRequest().getSession();
//
//			session.setAttribute(Constants.ISSUE, issue);
//
//			jump(Constants.URL_VIEW_ISSUE);
		} catch (NumberFormatException | ParameterNotFoundException e) {
			e.printStackTrace();
			jump(Constants.URL_ERROR);
		} catch (DaoException e) {

			e.printStackTrace();
			jump(Constants.URL_ERROR);
		}
	}



}
