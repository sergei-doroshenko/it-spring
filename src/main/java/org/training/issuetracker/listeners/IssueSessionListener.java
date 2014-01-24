package org.training.issuetracker.listeners;

import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
	private HttpSession session;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		Localizer localizer = null;
		try {
			localizer = LocalizerFactory.getLocalizer(Constants.DEFAULT_LANGUAGE);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = se.getSession();


		ResourceBundle resource = localizer.getBundle();
		Map<String, String> map = new HashMap<String, String>();

        Enumeration<String> keys = resource.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            map.put(key, resource.getString(key));
            //logger.info("key = " + key + "; value = " + resource.getString(key));
        }



		session.setAttribute("map", map);

		logger.info("Created session; Id = " + session.getId());
		logger.debug("Set localizer " + localizer.getClass().getSimpleName());

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("Created destroyed; Id = " + session.getId());
	}

}
