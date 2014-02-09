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
		commandMap.put(Constants.COMMAND_VIEW_ISSUE, new ViewIssueCommand(request, response));
		commandMap.put(Constants.COMMAND_VIEW_ISSUE_LIST, new ViewIssueListCommand(request, response));
		commandMap.put(Constants.COMMAND_LOGIN, new LoginCommand(request, response));
		commandMap.put(Constants.COMMAND_LOGOUT, new LogoutCommand(request, response));
		commandMap.put(Constants.COMMAND_INSERT_ISSUE, new InsertIssueCommand(request, response));
		commandMap.put(Constants.COMMAND_SUBMIT_ISSUE, new EditIssueCommand(request, response));
		commandMap.put(Constants.COMMAND_EDIT_ISSUE, new EditIssueCommand(request, response));
		commandMap.put(Constants.COMMAND_UPDATE_ISSUE, new UpdateIssueCommand(request, response));
		commandMap.put(Constants.COMMAND_DELETE_ISSUE, new DeleteIssueCommand(request, response));
		commandMap.put(Constants.COMMAND_VIEW_USER, new ViewUserCommand(request, response));
		commandMap.put(Constants.COMMAND_VIEW_USERS_LIST, new ViewUsersListCommand(request, response));
		commandMap.put(Constants.COMMAND_ADD_USER, new InsertUserCommand(request, response));
		commandMap.put(Constants.COMMAND_EDIT_USER, new EditUserCommand(request, response));
		commandMap.put(Constants.COMMAND_GET_PROJECT_BUILDS, new GetProjectBuildsCommand(request, response));
		commandMap.put(Constants.COMMAND_VIEW_STATUSES_LIST, new ViewStatusesListCommand(request, response));
		commandMap.put(Constants.COMMAND_EDIT_STATUS, new EditStatusCommand(request, response));
	}

	/**Method for call specific command object.
	 * @param commandName - String name of command
	 * @return command object
	 */
	public Command getCommand(String commandName) {
		return commandMap.get(commandName);
	}
}
