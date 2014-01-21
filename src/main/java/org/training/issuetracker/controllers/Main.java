package org.training.issuetracker.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.command.Command;
import org.training.issuetracker.command.WebCommandFactory;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.utils.ParameterParser;

/**
 * Servlet implementation class Main
 */
@WebServlet(value="/Main.do", loadOnStartup=1)
public class Main extends AbstractBaseController {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		//ConstantsXML.RESOURCE_REAL_PATH = config.getServletContext().getRealPath(Constants.ROOT_PATH);
	}

	@Override
	void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(Constants.RESOURCE_REAL_PATH);
		ParameterParser parser = new ParameterParser(request);
		try {
			String commandName = parser.getStringParameter("command");
			WebCommandFactory comFactory = new WebCommandFactory(request, response);
			Command command = comFactory.getCommand(commandName);
			command.execute();
		} catch (ParameterNotFoundException e) {
			e.printStackTrace();
			super.jumpError(Constants.ERROR_SOURCE, request, response);
		}

		return;
	}

}
