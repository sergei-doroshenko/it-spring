package org.training.issuetracker.listeners;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.exceptions.AppInitException;


/**Listener of application start or stop.
 * @author Sergei_Doroshenko
 */
@WebListener
public class ApplContextListener implements ServletContextListener {
	Logger logger = Logger.getLogger("org.training.issuetracker");
	/* This method executes on application init.
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		PatternLayout layout = new PatternLayout("%d %p [%c] %X{server.context} %m%n");
		String fileUrl = Constants.getRealPath() + "/WEB-INF/application.log";

		try {
			FileAppender appender = new FileAppender(layout, fileUrl,false);
			logger.addAppender(appender);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ServletContext ctx = servletContextEvent.getServletContext();
		Constants.setRealPath(ctx.getRealPath(Constants.ROOT_PATH));
		logger.debug("App real path = " + Constants.getRealPath());

		Map<String, Object> constantsMap = Constants.getConstMap();
		if (constantsMap != null) {
			logger.debug("Constants map create succesfully!");
			ctx.setAttribute("constants", constantsMap);
		} else {
			logger.fatal(Constants.CONSTANTS_MAP_INIT_ERR);
			throw new AppInitException ();
		}

	}

	/* This method executes on application destroy.
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

}
