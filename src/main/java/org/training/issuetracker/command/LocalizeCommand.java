package org.training.issuetracker.command;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.utils.ParameterParser;

/**Class to process localization action.
 * @author Sergei_Doroshenko
 *
 */
public class LocalizeCommand extends AbstractWebCommand {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.command");

	/**Inherited constructor.
	 * @param request - HttpServletRequest.
	 * @param response - HttpServletResponse.
	 */
	public LocalizeCommand(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException, ServletException {
		ParameterParser parser = new ParameterParser(getRequest());

		try {
			String langParam = parser.getStringParameter(Constants.KEY_LANGUAGE);
			Locale locale = new Locale(langParam);
			getResponse().setLocale(locale);
			//getRequest().getSession().setAttribute("locale", locale);
			String url = (String) getRequest().getSession().getAttribute("lastUrl");
			logger.debug("Set locale " + locale.getLanguage());
			logger.debug("Jump to url " + url);
			jump(url);
		} catch (ParameterNotFoundException e) {
			logger.error("Cannot set Locale!" + e.getMessage());
			jump(Constants.URL_ERROR);
		}

	}

}
