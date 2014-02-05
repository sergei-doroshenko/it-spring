package org.training.issuetracker.filters;

import java.io.IOException;
import java.util.Locale;

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
import org.training.issuetracker.constants.Constants;
@WebFilter(urlPatterns = {"/*"})
public class LocaleFilter implements Filter {

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
			Locale locale = null;
			String langParam = httpRequest.getParameter(Constants.COMMAND_LOCALE);

			logger.debug(langParam);


			if (langParam == null || langParam.isEmpty()) {
				locale = (Locale) session.getAttribute(Constants.KEY_LOCALE);
				if (locale == null) {
					locale = new Locale(Constants.DEFAULT_LANGUAGE);
					session.setAttribute(Constants.KEY_LOCALE, locale);
				}
			} else {
				locale = new Locale(langParam.toLowerCase(), langParam);
				session.setAttribute(Constants.KEY_LOCALE, locale);
			}

			locale = (Locale) session.getAttribute(Constants.KEY_LOCALE);
			logger.debug("Set locale " + locale.getLanguage() + " " + locale.getDisplayLanguage());


//			logger.debug(
//					httpRequest.getContextPath() + " 2 "
//					+ httpRequest.getServletPath() + " 3"
//					+ httpRequest.getRequestURI() + " 4 "
//					+ httpRequest.getRequestURL() + " 5 "
//					+ httpRequest.getQueryString()
//
//					);

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
