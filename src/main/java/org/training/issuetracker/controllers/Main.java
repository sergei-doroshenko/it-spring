package org.training.issuetracker.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.issuetracker.command.Command;
import org.training.issuetracker.command.WebCommandFactory;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.utils.ParameterParser;

/**
 * Servlet implementation class Main.
 * Common gate in application.
 */
@WebServlet(value = "/Main.do", loadOnStartup = 1)
public class Main extends AbstractBaseController {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger("org.training.issuetracker.controllers");

	@Override
	public void init(ServletConfig config) throws ServletException {
		//ConstantsXML.RESOURCE_REAL_PATH = config.getServletContext().getRealPath(Constants.ROOT_PATH);
	}

	@Override
	void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ParameterParser parser = new ParameterParser(request);
		try {
			String commandName = parser.getStringParameter(Constants.KEY_COMMAND);

			logger.info("Invoke -> " + commandName);
			WebCommandFactory comFactory = new WebCommandFactory(request, response);
			Command command = comFactory.getCommand(commandName);
//			logger.info("Create -> " + command.getClass().getSimpleName());
			logger.info("Create -> " + command);
			command.execute();
		} catch (ParameterNotFoundException e) {
			PrintWriter out = response.getWriter();
			out.print(e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		return;
	}

}
