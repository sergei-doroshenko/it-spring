package org.training.issuetracker.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.utils.JSONCreator;
import org.training.issuetracker.utils.ParameterParser;

/**
 * Servlet implementation class IssueController
 */
@WebServlet("/Issue.do")
public class IssueController extends AbstractBaseController {
	private static final long serialVersionUID = 1L;

	@Override
	void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);

		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		ParameterParser parser = new ParameterParser(request);

		try {
			long id = parser.getLongParameter("id");
			JsonObject json = JSONCreator.createSingleIssueJson(user, id);
			System.out.println(json);
			out.print(json);
			out.flush();
		} catch (NumberFormatException | ParameterNotFoundException e) {
			out.print(e.getMessage());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

		out.close();

	}

}
