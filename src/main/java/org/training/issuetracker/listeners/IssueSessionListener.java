package org.training.issuetracker.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.inter.Localizer;
import org.training.issuetracker.inter.LocalizerFactory;

/**Issue session listener.
 * @author Sergei_Doroshenko
 *
 */
@WebListener
public class IssueSessionListener implements HttpSessionListener {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.listeners");
	private HttpSession session;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		Localizer localizer = LocalizerFactory.getLocalizer(Constants.DEFAULT_LANGUAGE);
		HttpSession session = se.getSession();
		session.setAttribute(Constants.KEY_LOCALIZER, localizer);
		logger.info("Created session; Id = " + session.getId());
		logger.debug("Set localizer " + localizer.getClass().getSimpleName());

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("Created destroyed; Id = " + session.getId());
	}

}
