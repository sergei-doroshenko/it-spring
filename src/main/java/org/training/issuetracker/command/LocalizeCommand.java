package org.training.issuetracker.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.ParameterNotFoundException;
import org.training.issuetracker.inter.Localizer;
import org.training.issuetracker.inter.LocalizerFactory;
import org.training.issuetracker.inter.LocalizerLanguage;
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
	public LocalizeCommand(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	@Override
	public void execute() throws IOException {
		ParameterParser parser = new ParameterParser(getRequest());

		try {
			String langParam = parser.getStringParameter(Constants.KEY_LANGUAGE);
			LocalizerLanguage lang = LocalizerLanguage.valueOf(langParam.toUpperCase());
			Localizer localizer = LocalizerFactory.getLocalizer(lang);
			getRequest().getSession().setAttribute(Constants.KEY_LOCALIZER, localizer);
			logger.debug("Set localizer " + localizer.getClass().getSimpleName());
			logger.debug(localizer.getElementValue("elem"));
		} catch (ParameterNotFoundException e) {
			logger.error("Cannot get Localizer!" + e.getMessage());
		}

	}

}
