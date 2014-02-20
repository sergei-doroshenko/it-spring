package org.training.issuetracker.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.data.db.PropImplDB.PropertyType;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.DAOFactory;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;

import flexjson.JSONSerializer;

@Controller
@RequestMapping("/user")
public class UserController {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(method = RequestMethod.POST, params={"login", "password"}, produces="application/json")
	public @ResponseBody String login(@RequestParam(Constants.KEY_LOGIN) String login, 
			@RequestParam(Constants.KEY_PASSWORD) String password, HttpSession session) throws DaoException {
		
		User user = userDAO.getUser(login, password);
		
		logger.debug("Spring controller return user: " + user);
		session.setAttribute(Constants.KEY_USER, user);
		return "";
	}
	
	@RequestMapping(method = RequestMethod.GET, params="id", produces="application/json")
	public @ResponseBody String getUser(@RequestParam(Constants.KEY_ID) long id, @ModelAttribute(Constants.KEY_USER) User currentUser) throws DaoException {
		
		User user = userDAO.getUser(id);
		String json = new JSONSerializer().exclude("*.password").serialize(user);
		return json;
	}
	
	@RequestMapping(method = RequestMethod.POST, params={Constants.KEY_FIRST_NAME, Constants.KEY_LAST_NAME, Constants.KEY_EMAIL, Constants.KEY_PASSWORD},
			produces="application/json")
	public @ResponseBody String addUser(@RequestParam(Constants.KEY_FIRST_NAME) String firstName, @RequestParam(Constants.KEY_LAST_NAME) String lastName, 
			@RequestParam(Constants.KEY_EMAIL) String email, @RequestParam(Constants.KEY_PASSWORD) String password, 
			@ModelAttribute(Constants.KEY_USER) User currentUser, HttpSession session) throws DaoException {
		
		User user = new User ();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		PropDAO propDAO = DAOFactory.getDAO(PropDAO.class);
		Role role = (Role) propDAO.getProp(PropertyType.ROLE, Constants.DEFAULT_ROLE_ID);
		user.setRole(role);
		userDAO.insertUser(user);
		
		if (currentUser == null) {
			session.setAttribute(Constants.KEY_USER, user);
		}
		
		return "";
	}
	
	@ExceptionHandler(DaoException.class)
	public ResponseEntity<String> handleDaoException(DaoException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
	public ResponseEntity<String> handleParamsException(UnsatisfiedServletRequestParameterException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
