package org.training.issuetracker.listeners;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.training.issuetracker.constants.Constants;


/**Listener of application start or stop.
 * @author Sergei_Doroshenko
 */
@WebListener
public class ApplContextListener implements ServletContextListener {

	/* This method executes on application init.
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext ctx = servletContextEvent.getServletContext();
		Constants.RESOURCE_REAL_PATH = ctx.getRealPath(Constants.ROOT_PATH);

		PatternLayout layout = new PatternLayout("%d %p [%c] %X{server.context} %m%n");
		String fileUrl = Constants.RESOURCE_REAL_PATH + "/WEB-INF/application.log";
		Logger logger = Logger.getLogger(ApplContextListener.class);
		try {
			FileAppender appender = new FileAppender(layout, fileUrl,false);
			logger.addAppender(appender);
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.debug(Constants.RESOURCE_REAL_PATH);

	}

	/* This method executes on application destroy.
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

}
