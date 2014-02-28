package org.training.issuetracker.controllers;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.beans.MethodInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.JqGridData;
import org.training.issuetracker.utils.SearchFilterParams;
import org.training.issuetracker.validation.Email;
import org.training.issuetracker.validation.EmailValidator;
import org.training.issuetracker.validation.UserValidator;

import flexjson.JSONSerializer;

@Controller
@Validated
@RequestMapping("/user")
public class UserController {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PropDAO propDAO;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private EmailValidator emailValidator;
	
	private class LongEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String arg) throws IllegalArgumentException {
			long result = 0;
			if (!arg.equals("_empty")) {
				result = Long.parseLong(arg);
			}
			setValue(result);
		}
	}
	
	private class RoleEditor extends PropertyEditorSupport {
		
		@Override
		public void setAsText(String arg) throws IllegalArgumentException {
			long roleId = Constants.DEFAULT_ROLE_ID;
			
			if (!arg.isEmpty()) {
				roleId = Long.parseLong(arg);
			}
			
			Role tempRole = null;
			try {
				tempRole = (Role) propDAO.getProp(PropertyType.ROLE, roleId);
			} catch (DaoException e) {
				throw new IllegalArgumentException("No such role!");
			}
			setValue(tempRole);
		}
	}
	
	@InitBinder("user") 
    private void initBinder(WebDataBinder binder) {  
        binder.setValidator(userValidator);
        binder.registerCustomEditor(Long.class, "id", new LongEditor());
        binder.registerCustomEditor(Role.class, "role", new RoleEditor()); 
    } 
	
	@InitBinder("login")
	private void initBider(WebDataBinder binder) {
		binder.setValidator(emailValidator);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, params={"login", "password"}, produces="application/json")
	public @ResponseBody String getUserByLogin(@Email @RequestParam(Constants.KEY_LOGIN)String login,  
			@RequestParam(Constants.KEY_PASSWORD) String password, HttpSession session) throws DaoException {
		
		User user = userDAO.getUser(login, password);
		
		session.setAttribute(Constants.KEY_USER, user);
		return "";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces="application/json")
	public RedirectView loguot(HttpSession session) {
		
		session.removeAttribute(Constants.KEY_USER);
		session.invalidate();
		return new RedirectView("/index.jsp", true);
	}
	
	@RequestMapping(method = RequestMethod.GET, params="id", produces="application/json")
	public @ResponseBody String getUserById(@RequestParam(Constants.KEY_ID) long id) throws DaoException {
		
		User user = userDAO.getUser(id);
		String json = new JSONSerializer().exclude("*.password", "*.class").serialize(user);
		return json;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
//	public @ResponseBody String getUsersList(@RequestParam("rows") int rows, @RequestParam("page") int page,
//			@RequestParam("sidx") String sidx, @RequestParam("sord") String sord) throws DaoException {			
	public @ResponseBody String getUsersList(SearchFilterParams params) throws DaoException {		
		
		int records = userDAO.getUserRecordsCount();
		
		List<User> users = userDAO.getUsersList(params);
		
		int total = (int) Math.ceil((double)records/(double) params.getRows());			
		JqGridData<User> data = new JqGridData<>(total, params.getPage(), records, users);
		String json = data.getJsonString();
		return json;
	}
	
//	@RequestMapping(value="/edit", method = RequestMethod.POST, params={"oper=add", Constants.KEY_FIRST_NAME, 
//			Constants.KEY_LAST_NAME, Constants.KEY_EMAIL, Constants.KEY_PASSWORD, Constants.KEY_ROLE_ID}, produces="application/json")
//	public @ResponseBody String addUser(@RequestParam(Constants.KEY_FIRST_NAME) String firstName, @RequestParam(Constants.KEY_LAST_NAME) String lastName, 
//			@RequestParam(Constants.KEY_EMAIL) String email, @RequestParam(Constants.KEY_PASSWORD) String password, 
//			@RequestParam(Constants.KEY_ROLE_ID) long roleId, HttpSession session) throws DaoException {
//	public @ResponseBody String addUser(String firstName, String lastName, 
//			String email, String password, 
//			long role, HttpSession session) throws DaoException {
//	@RequestMapping(value="/edit", method = RequestMethod.POST, params={"oper=add"}, produces="application/json")
//	public @ResponseBody String addUser(@ModelAttribute User user, HttpSession session) throws DaoException {
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={"oper=add"}, produces="application/json")
//	public @ResponseBody String addUser(@Valid User user, BindingResult bindingResult, HttpSession session) throws DaoException {
	public ResponseEntity<String> addUser(@Valid User user, BindingResult bindingResult, HttpSession session) throws DaoException {
		
		if(bindingResult.hasErrors()){
			String json = new JSONSerializer().exclude("*.class", "bindingFailure", "code", "objectName", "rejectedValue")
					.serialize(bindingResult.getFieldErrors());
			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);
		}
		
		userDAO.insertUser(user);
				
		User currentUser = (User) session.getAttribute(Constants.KEY_USER);
		
		if (currentUser == null) {
			session.setAttribute(Constants.KEY_USER, user);
		}
		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={"oper=edit", Constants.KEY_ID, Constants.KEY_FIRST_NAME, 
			Constants.KEY_LAST_NAME, Constants.KEY_EMAIL, Constants.KEY_PASSWORD, Constants.KEY_ROLE_ID}, produces="application/json")
//	public @ResponseBody String editUser(@RequestParam(Constants.KEY_ID) long id, @RequestParam(Constants.KEY_FIRST_NAME) String firstName, 
//			@RequestParam(Constants.KEY_LAST_NAME) String lastName, @RequestParam(Constants.KEY_EMAIL) String email, 
//			@RequestParam(Constants.KEY_PASSWORD) String password, @RequestParam(Constants.KEY_ROLE_ID) long roleId, 
//			HttpSession session) throws DaoException {
	public ResponseEntity<String> editUser(@Valid User user, BindingResult bindingResult,	HttpSession session) throws DaoException {
		
//		User user = new User ();
//		user.setId(id);
//		user.setFirstName(firstName);
//		user.setLastName(lastName);
//		user.setEmail(email);
//		user.setPassword(password);
//		
//		if(roleId == 0) {
//			roleId = Constants.DEFAULT_ROLE_ID;
//		}
//		
//		Role role = (Role) propDAO.getProp(PropertyType.ROLE, roleId);
//		user.setRole(role);

		if(bindingResult.hasErrors()){
			String json = new JSONSerializer().exclude("*.class", "bindingFailure", "code", "objectName", "rejectedValue")
					.serialize(bindingResult.getFieldErrors());
			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);
		}
		
		userDAO.updateUser(user);
				
		User currentUser = (User) session.getAttribute(Constants.KEY_USER);
		
		if (!currentUser.getRole().getName().equals(Constants.ROLE_ADMIN)) {
			session.removeAttribute(Constants.KEY_USER);
			session.setAttribute(Constants.KEY_USER, user);
		}
		
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={"oper=del", Constants.KEY_ID}, produces="application/json")
	public @ResponseBody String deleteUser(@RequestParam(Constants.KEY_ID) long id) throws DaoException {
		
		userDAO.deleteUser(id);
				
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
	
	@ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<String> handleException(ConstraintViolationException ex) {
		String message = "";
		for (ConstraintViolation<?> failure : ex.getConstraintViolations()) {
			message += failure.getMessage() + " ";
		}
		
        return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
    }
}
