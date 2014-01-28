package org.training.issuetracker.listeners;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.i18n.Localizer;
import org.training.issuetracker.i18n.LocalizerFactory;
/**Issue session listener.
 * @author Sergei_Doroshenko
 *
 */
@WebListener
public class IssueSessionListener implements HttpSessionListener {
	private final Logger logger = Logger.getLogger("org.training.issuetracker.listeners");

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		Localizer localizer = null;
		try {
			localizer = LocalizerFactory.getLocalizer(Constants.DEFAULT_LANGUAGE);
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		HttpSession session = se.getSession();

		Map<String, String> map;
		try {
			map = localizer.getValuesMap();
			session.setAttribute("map", map);
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		logger.info("Created session; Id = " + session.getId());
		logger.debug("Set localizer " + localizer.getClass().getSimpleName());

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("Created destroyed; Id = " + se.getSession().getId());
	}

}
