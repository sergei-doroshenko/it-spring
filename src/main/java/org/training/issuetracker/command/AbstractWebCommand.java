package org.training.issuetracker.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.training.issuetracker.exceptions.DaoException;

/**Class of abstract web command contains request.
 * and response for child web commands
 * @author Sergei_Doroshenko
 *
 */
public abstract class AbstractWebCommand implements Command {
	/**HttpServletRequest that comes from client.
	 *
	 */
	private final HttpServletRequest request;

	/**HttpServletResponse that will.
	 * send to client
	 */
	private final HttpServletResponse response;

	/**HttpSession for command objects usage.
	 *
	 */
	private final HttpSession session;

	/**General purpose constructor.
	 * @param request is an HttpServletRequest
	 * @param response is an HttpServletResponse
	 */
	public AbstractWebCommand(HttpServletRequest request,
			HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	/* Method that execute command logic
	 * will implement in subclasses
	 * @see org.training.issuetracker.command.Command#execute()
	 */
	@Override
	public void execute() throws IOException, ServletException {

	}

	/**
	 * @return HttpServletRequest object
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * @return HttpServletResponse
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @return HttpSession
	 */
	public HttpSession getSession() {
		return session;
	}

	protected void jump(String url) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
