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

public class EditIssueCommand extends AbstractWebCommand {

	public EditIssueCommand(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException, ServletException {
		ParameterParser parser = new ParameterParser(getRequest());

		try {
			String commandName = parser.getStringParameter(Constants.KEY_COMMAND);
			if (commandName.equals(Constants.COMMAND_SUBMIT_ISSUE)) {
				jump(Constants.URL_EDIT_ISSUE);
			}
			
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
			jump(Constants.URL_ERROR);
		}
	}
	
	

}
