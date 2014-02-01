package org.training.issuetracker.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.data.xml.DataStorage;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.utils.ParameterParser;

/**Class for get one issue specified by id.
 * @author Sergei_Doroshenko
 *
 */
public class ViewIssueCommand extends AbstractWebCommand {
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

		//getResponse().setContentType(MediaType.APPLICATION_JSON);
		//PrintWriter out = getResponse().getWriter();
		ParameterParser parser = new ParameterParser(getRequest());

		try {
			long id = parser.getLongParameter(Constants.KEY_ID);
			DataStorage data = DataStorage.getInstance();
			Issue issue = data.getIssue(id);
			//getRequest().setAttribute(Constants.ENTITY_TYPE, Constants.ISSUE_TYPE);
			//getRequest().setAttribute(Constants.ENTITY, issue);
			HttpSession session = getRequest().getSession();
			//session.setAttribute(Constants.ENTITY_TYPE, Constants.ISSUE);
			session.setAttribute(Constants.ISSUE, issue);

			//JsonObject json = JSONCreator.createSingleIssueJson(id);
			System.out.println(issue);
			//out.print(json);
			//out.flush();
			jump(Constants.URL_VIEW_ISSUE);
		} catch (NumberFormatException | ParameterNotFoundException e) {
			jump(Constants.URL_ERROR);
			//out.print(e.getMessage());
			//getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}


		//out.close();

	}

}
