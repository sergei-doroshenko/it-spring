package org.training.issuetracker.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;

@Controller
@RequestMapping("/user")
public class UserController {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(method = RequestMethod.POST, produces="application/json")
	public @ResponseBody String getUser(@RequestParam(Constants.KEY_LOGIN) String login, 
			@RequestParam(Constants.KEY_PASSWORD) String password, HttpSession session) throws DaoException {
		
		User user = userDAO.getUser(login, password);
		
		if(user.getId() == 0) {
			throw new DaoException(Constants.ERROR_FIND_USER);
		}
		logger.debug("Spring controller return user: " + user);
		session.setAttribute(Constants.KEY_USER, user);
		return "";
	}
	
	@ExceptionHandler(DaoException.class)
	public ResponseEntity<String> handleIOException(DaoException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
