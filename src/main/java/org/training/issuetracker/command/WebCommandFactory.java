package org.training.issuetracker.command;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;

/**Class for creating commands.
 * @author Sergei_Doroshenko
 *
 */
public class WebCommandFactory {
	/**Map contains commands.
	 */
	private final HashMap<String, Command> commandMap = new HashMap<String, Command>();

	/**CommandFactory constructor that populate commandMap during factory creating.
	 * @param request - an HttpServletRequest
	 * @param response - an HttpServletResponse
	 */
	public WebCommandFactory(HttpServletRequest request, HttpServletResponse response) {
		commandMap.put("issue", new ViewIssueCommand(request, response));
		commandMap.put("issuelist", new ViewIssueListCommand(request, response));
		commandMap.put("login", new LoginCommand(request, response));
		commandMap.put("logout", new LogoutCommand(request, response));
		commandMap.put("localize", new LocalizeCommand(request, response));
		commandMap.put(Constants.COMMAND_SUBMIT_ISSUE, new EditIssueCommand(request, response));
		commandMap.put(Constants.COMMAND_EDIT_ISSUE, new EditIssueCommand(request, response));
		commandMap.put(Constants.COMMAND_VIEW_USER, new ViewUserCommand(request, response));
	}

	/**Method for call specific command object.
	 * @param commandName - String name of command
	 * @return command object
	 */
	public Command getCommand(String commandName) {
		return commandMap.get(commandName);
	}
}
