package org.training.issuetracker.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;

public class ViewUserCommand extends AbstractWebCommand {

	public ViewUserCommand(HttpServletRequest request,HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException, ServletException {
		
		jump(Constants.URL_VIEW_USER);
	}
	
	

}
