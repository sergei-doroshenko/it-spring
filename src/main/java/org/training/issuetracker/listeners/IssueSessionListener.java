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
//		Localizer localizer = null;
//		try {
//			localizer = LocalizerFactory.getLocalizer(Constants.DEFAULT_LANGUAGE);
//		} catch (MalformedURLException e) {
//
//			e.printStackTrace();
//		}		
//
//		Map<String, String> map;
//		try {
//			map = localizer.getValuesMap();
//			session.setAttribute("map", map);
//		} catch (UnsupportedEncodingException e) {
//
//			e.printStackTrace();
//		}	
//		logger.debug("Set localizer " + localizer.getClass().getSimpleName());
		logger.info("Created session; Id = " + session.getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("Created destroyed; Id = " + se.getSession().getId());
	}

}
