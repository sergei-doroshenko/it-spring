package org.training.issuetracker.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;

/**
 * Servlet implementation class AbstractBaseController
 */
abstract class AbstractBaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	abstract void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

	protected void jump(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}

	protected void jumpError(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);
		RequestDispatcher rd = getServletContext().getRequestDispatcher(Constants.JUMP_ERROR);
		rd.forward(request, response);
	}
}
