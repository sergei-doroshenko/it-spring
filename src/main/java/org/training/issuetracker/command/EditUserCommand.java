package org.training.issuetracker.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.utils.ParameterParser;

public class EditUserCommand extends AbstractWebCommand {
	private Logger logger = Logger.getLogger("org.training.issuetracker.command");

	public EditUserCommand(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException, ServletException {
		PrintWriter out = getResponse().getWriter();
		ParameterParser parser = new ParameterParser(getRequest());
		User user = (User) getRequest().getSession().getAttribute(Constants.KEY_USER);
		
		out.print(Constants.URL_MAIN);
	}
}
