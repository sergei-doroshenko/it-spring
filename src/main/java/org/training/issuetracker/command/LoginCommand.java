package org.training.issuetracker.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.exceptions.ValidationException;
import org.training.issuetracker.utils.JSONCreator;
import org.training.issuetracker.utils.ParameterInspector;
import org.training.issuetracker.utils.ParameterParser;

/**Class that implement login logic.
 * @author Sergei_Doroshenko
 *
 */
public class LoginCommand extends AbstractWebCommand {
	/**Constructor from superclass.
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
	public LoginCommand(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	/* Method get login and password parameters from request.
	 * And get User object from DAO
	 * @see org.training.issuetracker.command.AbstractWebCommand#execute()
	 */
	@Override
	public void execute() throws IOException {
		System.out.println("Start");
		getResponse().setContentType(MediaType.APPLICATION_JSON);
		PrintWriter out = getResponse().getWriter();
		ParameterParser parser = new ParameterParser(getRequest());

		try {
			String login = parser.getStringParameter(Constants.KEY_LOGIN);
			String password = parser.getStringParameter(Constants.KEY_PASSWORD);
			System.out.println(login + " - " + password);
			ParameterInspector.checkEmail(login);
			ParameterInspector.checkPassword(password);
			UserDAO userDAO = DAOFactory.getDAO(UserDAO.class);
			System.out.println(userDAO);
			User user = userDAO.getUser(login, password);
			System.out.println(user);
			if (user != null) {
				getSession().setAttribute(Constants.KEY_USER, user);
				Cookie userCookie = new Cookie(Constants.KEY_USER, user.toJsonObj().toString());
				getResponse().addCookie(userCookie);
				System.out.println(JSONCreator.createUserData(user));
				out.print(JSONCreator.createUserData(user));
			} else {
				getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}

		} catch (DaoException e) {
			e.printStackTrace();
			out.print(e.getMessage());
			getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
		} catch (ValidationException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			out.print(e.getMessage());
			getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
		} catch (ParameterNotFoundException e) {
			out.print(e.getMessage());
			getResponse().setStatus(HttpServletResponse.SC_BAD_REQUEST);
			
		} finally {
			out.flush();
			out.close();
		}
	}
}
