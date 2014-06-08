package org.training.issuetracker.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.training.issuetracker.constants.Constants;

public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		Locale locale = LocaleContextHolder.getLocale();
		
		String errorMessage = messageSource.getMessage("user.err.badcredentials", null, locale);
//		if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
//			errorMessage = "USER NOT FOUND";
//		} else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
//			errorMessage = "USER_DISABLED";
//		} else if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
//			errorMessage = "BAD_CREDENTIALS";
//		}
		
		session.setAttribute(Constants.USER_MESSAGE, errorMessage);
		setDefaultFailureUrl("/index.jsp");
		super.onAuthenticationFailure(request, response, exception);
	
	}

}
