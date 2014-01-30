package org.training.issuetracker.command;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.training.issuetracker.constants.Constants;

/**Class implements log out user logic.
 * @author Sergei_Doroshenko
 *
 */
public class LogoutCommand extends AbstractWebCommand {
	/**Constructor from superclass.
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 */
	public LogoutCommand(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException {
		getSession().removeAttribute(Constants.KEY_USER);
		getSession().invalidate();
		getResponse().addCookie(new Cookie(Constants.KEY_USER, null));
		getResponse().sendRedirect("index.jsp");
		return;
	}


}
