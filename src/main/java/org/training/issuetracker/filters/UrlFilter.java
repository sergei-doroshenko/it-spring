package org.training.issuetracker.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
@WebFilter(urlPatterns = {"/*"})
public class UrlFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		Logger logger = Logger.getLogger("org.training.issuetracker.filters");
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {

			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			HttpSession session = httpRequest.getSession();

			String command = httpRequest.getParameter("command");
			logger.debug(command);
			String lastUrl = null;


			if((command != null) && !(command.equals("localize"))) {
				if (command.equals("issuelist")) {
					lastUrl = httpRequest.getServletPath();
				} else {
					lastUrl = httpRequest.getServletPath() + "?" + httpRequest.getQueryString();
				}
				session.setAttribute("lastUrl", lastUrl);
			}

//			logger.debug(
//					httpRequest.getContextPath() + " 2 "
//					+ httpRequest.getServletPath() + " 3"
//					+ httpRequest.getRequestURI() + " 4 "
//					+ httpRequest.getRequestURL() + " 5 "
//					+ httpRequest.getQueryString()
//
//					);

			logger.debug(lastUrl);

			chain.doFilter(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
