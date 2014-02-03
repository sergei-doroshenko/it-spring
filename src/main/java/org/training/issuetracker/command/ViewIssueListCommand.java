package org.training.issuetracker.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.JSONCreator;

/**Command class for get list of Issue objects.
 * @author Sergei_Doroshenko
 *
 */
public class ViewIssueListCommand extends AbstractWebCommand {

	/**Constructor from superclass.
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
	public ViewIssueListCommand(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	/* Method get user id from url string, get Issue objects list.
	 * And serialized it to JSON object
	 * @see org.training.issuetracker.command.AbstractWebCommand#execute()
	 */
	@Override
	public void execute() throws IOException {
		getResponse().setContentType(MediaType.APPLICATION_JSON);
		User user = (User) getSession().getAttribute(Constants.KEY_USER);
		JsonObject json = null;
		try {
			json = JSONCreator.createIssueJsonList(user);
		} catch (DaoException e) {
			
			e.printStackTrace();
		}
		PrintWriter out = getResponse().getWriter();
		out.print(json);
		out.flush();
		out.close();
	}
}
