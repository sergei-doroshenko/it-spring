package org.training.issuetracker.controllers;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.JqGridData;
import org.training.issuetracker.utils.SearchFilterParams;
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
	private MessageSource messageSource;
	
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
				e.printStackTrace();
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
	

	
//	@RequestMapping(value = "/login", method = RequestMethod.POST, params={"login", "password"}, produces="application/json")
//	public @ResponseBody String getUserByLogin(@RequestParam(Constants.KEY_LOGIN)String login,  
//			@RequestParam(Constants.KEY_PASSWORD) String password, HttpSession session) throws DaoException {
//		
//		User user = userDAO.getUser(login, password);
//		
//		session.setAttribute(Constants.KEY_USER, user);
//		return "";
//	}
	
//	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces="application/json")
//	public RedirectView loguot(HttpSession session) {
//		
//		session.removeAttribute(Constants.KEY_USER);
//		session.invalidate();
//		return new RedirectView("/index.jsp", true);
//	}
	
	@RequestMapping(method = RequestMethod.GET, params="id", produces="application/json")
	public @ResponseBody String getUserById(@RequestParam(Constants.KEY_ID) long id) throws DaoException {
		
		User user = userDAO.getUser(id);
		String json = new JSONSerializer().exclude("*.password", "*.class").serialize(user);
		return json;
	}
	
	@RequestMapping(method = RequestMethod.GET, params="_search", produces="application/json")		
	public @ResponseBody String getUsersList(SearchFilterParams params) throws DaoException {		
		
		int records = userDAO.getUserRecordsCount();
		
		List<User> users = userDAO.getUsersList(params);
		
		int total = (int) Math.ceil((double)records/(double) params.getRows());			
		JqGridData<User> data = new JqGridData<>(total, params.getPage(), records, users);
		String json = data.getJsonString();
		return json;
	}
	
	/**This method return users options for select html block.
	 * for table - jqgrid.
	 * @return html tags <select> with <options>
	 * @throws DaoException
	 */
	@RequestMapping(value="/options", method = RequestMethod.GET, produces="text/plain")
	public @ResponseBody String getUsersOptions() throws DaoException {
		String options = "<select>";
		List<User> users = userDAO.getUsersList();
		
		for (User user : users) {
			options += "<option value=" + user.getId() + ">" + user.getFirstName() 
					+ " " + user.getLastName() + "</option>";
		}
		options += "</select>";
		return options;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={"oper=add"}, produces="application/json")
	public ResponseEntity<String> addUser(@Valid User user, BindingResult bindingResult, 
			Principal principal, HttpSession session, Locale locale) throws DaoException {
		
		if(bindingResult.hasErrors()){
			String json = new JSONSerializer().exclude("*.class", "bindingFailure", "code", "objectName", "rejectedValue")
					.serialize(bindingResult.getFieldErrors());
			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);
		}
		
		if(user.getRole() == null) {
			user.setRole((Role) propDAO.getProp(PropertyType.ROLE, Constants.DEFAULT_ROLE_ID)); 
		}
		user.setEnabled(true);
		userDAO.insertUser(user);
		
		if(principal == null) {
			session.setAttribute(Constants.USER_MESSAGE, messageSource.getMessage("user.register.success", null, locale));
		}
				
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={"oper=edit"//, Constants.KEY_ID, Constants.KEY_FIRST_NAME, 
			}, produces="application/json")//Constants.KEY_LAST_NAME, Constants.KEY_EMAIL, Constants.KEY_PASSWORD
	public ResponseEntity<String> editUser(@Valid User user, BindingResult bindingResult, HttpSession session) throws DaoException {
		
		if(bindingResult.hasErrors()){
			String json = new JSONSerializer().exclude("*.class", "bindingFailure", "code", "objectName", "rejectedValue")
					.serialize(bindingResult.getFieldErrors());
			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);
		}
		
		if(user.getRole() == null) {
			user.setRole((Role) propDAO.getProp(PropertyType.ROLE, Constants.DEFAULT_ROLE_ID)); 
		}
		user.setEnabled(true);
		
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
