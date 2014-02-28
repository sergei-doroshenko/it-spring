package org.training.issuetracker.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.log4j.Logger;

/**Issue session listener.
 * @author Sergei_Doroshenko
 *
 */
@WebListener
public class IssueSessionListener implements HttpSessionListener {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.listeners");

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		logger.info("Created session; Id = " + session.getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("Destroyed session; Id = " + se.getSession().getId());
	}

}
