package org.training.issuetracker.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login.do")
public class LoginController extends AbstractBaseController {
	private static final long serialVersionUID = 1L;

	@Override
	void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();


		if(null == session.getAttribute(Constants.KEY_USER)) {
			ParameterParser parser = new ParameterParser(request);
//			String login = request.getParameter(Constants.KEY_LOGIN);
//			String password = request.getParameter(Constants.KEY_PASSWORD);

			try {
				String login = parser.getStringParameter(Constants.KEY_LOGIN);
				String password = parser.getStringParameter(Constants.KEY_PASSWORD);

				ParameterInspector.checkEmail(login);
				ParameterInspector.checkPassword(password);
				UserDAO userDAO = DAOFactory.getDAO(UserDAO.class);
				User user = userDAO.getUser(login, password);

				if(null == user){
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					out.print("NO USER" + Constants.LOGIN_OR_PASSWORD_WRONG);
//					jumpError(Constants.LOGIN_OR_PASSWORD_WRONG, request, response);
					return;
				}

				session.setAttribute(Constants.KEY_USER, user);
				Cookie userCookie = new Cookie("user", user.toJsonObj().toString());
				response.addCookie(userCookie);
				System.out.println(JSONCreator.createUserData(user));
				out.print(JSONCreator.createUserData(user));


			} catch (DaoException e) {
				out.print(e.getMessage());
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				//super.jumpError(Constants.ERROR_SOURCE, request, response);
				return;
			} catch (ValidationException e) {
				out.print( e.getMessage());
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

//				jumpError(e.getMessage(), request, response);
				return;
			} catch (ParameterNotFoundException e) {
				out.print(e.getMessage());
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

//				jumpError(e.getMessage(), request, response);
				return;
			} finally {
				out.flush();
				out.close();

			}
		} else {
			session.removeAttribute(Constants.KEY_USER);
			session.invalidate();
			response.sendRedirect("/issuetracker/");
			return;
		}
	}

	@Override
	protected void jumpError(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(Constants.JUMP_ERROR);
		rd.forward(request, response);
	}
}
