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
import org.training.issuetracker.domain.DAO.AttachmentDAO;
import org.training.issuetracker.domain.DAO.CommentDAO;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.utils.ParameterParser;

/**Class for get one issue specified by id.
 * @author Sergei_Doroshenko
 *
 */
public class ViewIssueCommand extends AbstractWebCommand {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.command");
	/**Constructor from superclass.
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
	public ViewIssueCommand(HttpServletRequest request,	HttpServletResponse response) {
		super(request, response);
	}
	/* Method get issue id from request receive Issue from DAO,
	 * serialize it to JSON, put into response and back to the client.
	 * @see org.training.issuetracker.command.Command#execute(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute() throws IOException, ServletException {

		ParameterParser parser = new ParameterParser(getRequest());

		try {
			long id = parser.getLongParameter(Constants.KEY_ID);
			IssueDAO dao = DAOFactory.getDAO(IssueDAO.class);

			Issue issue = dao.getIssue(id);

			HttpSession session = getRequest().getSession();

			getRequest().setAttribute(Constants.ISSUE, issue);

			logger.debug(issue);

			CommentDAO comDAO = DAOFactory.getDAO(CommentDAO.class);
			List<Comment> comments = comDAO.getCommentsList(id);

			getRequest().setAttribute(Constants.COMMENTS, comments);

			AttachmentDAO attchDAO = DAOFactory.getDAO(AttachmentDAO.class);
			List<Attachment> attachments = attchDAO.getAttachmentsList(id);

			getRequest().setAttribute(Constants.ATTACHMENTS, attachments);

			jump(Constants.URL_VIEW_ISSUE);
		} catch (NumberFormatException | ParameterNotFoundException e) {

			jump(Constants.URL_ERROR);
		} catch (DaoException e) {

			e.printStackTrace();
			jump(Constants.URL_ERROR);
		}

	}

}
