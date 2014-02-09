package org.training.issuetracker.security;

import java.util.Set;
import java.util.TreeSet;

import org.training.issuetracker.command.EditIssueCommand;
import org.training.issuetracker.command.EditUserCommand;
import org.training.issuetracker.command.GetProjectBuildsCommand;
import org.training.issuetracker.command.InsertIssueCommand;
import org.training.issuetracker.command.InsertUserCommand;
import org.training.issuetracker.command.LoginCommand;
import org.training.issuetracker.command.LogoutCommand;
import org.training.issuetracker.command.UpdateIssueCommand;
import org.training.issuetracker.command.ViewUserCommand;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.User;

public class PermissionInspector {
	private static Set<String> userPermissionSet = new TreeSet<String>();
	static {
		userPermissionSet.add(LoginCommand.class.getSimpleName());
		userPermissionSet.add(LogoutCommand.class.getSimpleName());
		userPermissionSet.add(InsertIssueCommand.class.getSimpleName());
		userPermissionSet.add(EditIssueCommand.class.getSimpleName());
		userPermissionSet.add(UpdateIssueCommand.class.getSimpleName());
		userPermissionSet.add(ViewUserCommand.class.getSimpleName());
		userPermissionSet.add(InsertUserCommand.class.getSimpleName());
		userPermissionSet.add(EditUserCommand.class.getSimpleName());
		userPermissionSet.add(GetProjectBuildsCommand.class.getSimpleName());		
	}
	
	public static boolean checkPermission (User user, String commandName) {
		boolean result = false;
		if (user.getRole().getName().equals(Constants.ROLE_ADMIN)) {
			result = true;
		} else if (user.getRole().getName().equals(Constants.ROLE_USER)) {
			result = userPermissionSet.contains(commandName);
		}
		
		return result;
	}
}
