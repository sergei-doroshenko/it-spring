package org.training.issuetracker.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.utils.JSONCreator;
import org.training.issuetracker.utils.ParameterParser;

/**Class for get one issue specified by id.
 * @author Sergei_Doroshenko
 *
 */
public class GetSpecIssueCommand extends AbstractWebCommand {
	/**Constructor from superclass.
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
	public GetSpecIssueCommand(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}
	/* Method get issue id from request receive Issue from DAO,
	 * serialize it to JSON, put into response and back to the client.
	 * @see org.training.issuetracker.command.Command#execute(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void execute() throws IOException {

		getResponse().setContentType(MediaType.APPLICATION_JSON);
		PrintWriter out = getResponse().getWriter();
		ParameterParser parser = new ParameterParser(getRequest());

		try {
			long id = parser.getLongParameter(Constants.KEY_ID);
			JsonObject json = JSONCreator.createSingleIssueJson(id);
			System.out.println(json);
			out.print(json);
			out.flush();
		} catch (NumberFormatException | ParameterNotFoundException e) {
			out.print(e.getMessage());
			getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

		out.close();

	}

}
