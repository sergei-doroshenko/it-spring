package org.training.issuetracker.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
		
		
		
//		HttpSession session = request.getSession();
		
//		if(exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
//			request.setAttribute(Constants.USER_MESSAGE, "BAD_CREDENTIAL");
//		} else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
//			request.setAttribute(Constants.USER_MESSAGE, "USER_DISABLED");
//		}
		setDefaultFailureUrl("/index.jsp");
		super.onAuthenticationFailure(request, response, exception);
	
	}

}
