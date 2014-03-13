package org.training.issuetracker.controllers;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		public void setAsText(String arg) {
			long roleId = Constants.DEFAULT_ROLE_ID;
			
			if (!arg.isEmpty() || arg == null) {
				roleId = Long.parseLong(arg);
			}
			
			Role tempRole = null;
			try {
				tempRole = (Role) propDAO.getProp(PropertyType.ROLE, roleId);
			} catch (DaoException e) {
				
				e.printStackTrace();
			}
			
			logger.debug("Editor return Role -----------------------------------------------------------------------------------------------------------------" + tempRole);
			
			setValue(tempRole);
		}
	}
	
	@InitBinder("user") 
    private void initBinder(WebDataBinder binder) {  
        binder.setValidator(userValidator);
        binder.registerCustomEditor(Long.class, "id", new LongEditor());
//        binder.registerCustomEditor(Role.class, "role", new RoleEditor()); 
    } 
	
	@PreAuthorize("hasAnyRole('USER','ADMINISTRATOR')")
	@RequestMapping(method = RequestMethod.GET, params="id", produces="application/json")
	public @ResponseBody String getUserById(@RequestParam(Constants.KEY_ID) long id) throws DaoException {
		
		User user = userDAO.getUser(id);
		String json = new JSONSerializer().exclude("*.password", "*.class").serialize(user);
		return json;
	}
	
	@PreAuthorize("hasRole('ADMINISTRATOR')")
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
	@PreAuthorize("hasAnyRole('USER','ADMINISTRATOR')")
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
		
//		if(bindingResult.hasErrors()){
//			String json = new JSONSerializer().exclude("*.class", "bindingFailure", "code", "objectName", "rejectedValue")
//					.serialize(bindingResult.getFieldErrors());
//			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);
//		}
		
		if(bindingResult.hasErrors()){
			String json = new JSONSerializer().exclude("*.class")
					.serialize(bindingResult.getFieldErrors());
			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);
		}
		
		user.setEnabled(true);
		
		logger.info("Add Role ------------------------------------------------------------------" + user.getRole());
		if (user.getRole() == null){
			user.setRole((Role) propDAO.getProp(PropertyType.ROLE, Constants.DEFAULT_ROLE_ID));
		}
		
		userDAO.insertUser(user);
		
		if(principal == null) {
			session.setAttribute(Constants.USER_MESSAGE, messageSource.getMessage("user.register.success", null, locale));
		}
				
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('USER','ADMINISTRATOR')")
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={"oper=edit"}, produces="application/json")
	public ResponseEntity<String> editUser(@Valid User user, BindingResult bindingResult, Principal principal,
			HttpSession session, HttpServletRequest request) throws DaoException {
		
		ResponseEntity<String> response = null;
		
		if(bindingResult.hasErrors()){
			String json = new JSONSerializer().exclude("*.class", "bindingFailure", "code", "objectName", "rejectedValue")
					.serialize(bindingResult.getFieldErrors());
			response = new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);
		}
		
//		final UserDetails currentDetails = (UserDetails) ((Authentication) principal).getPrincipal();
//		SecurityContextHolderAwareRequestWrapper wrapper = new SecurityContextHolderAwareRequestWrapper(request, null);
		//wrapper.isUserInRole(Constants.ROLE_ADMIN) ;
		
		Collection<? extends GrantedAuthority> authorities = ((Authentication) principal).getAuthorities();
		boolean isAdmin = authorities.contains(new SimpleGrantedAuthority("ADMINISTRATOR"));
		for (GrantedAuthority grantedAuthority : authorities) {
		      logger.info("Role ----------------------------------------------------" + grantedAuthority);
		    }
		if (isRolePresent(authorities, "ADMINISTRATOR")) {
			logger.info("Admin edit Role ------------------------------------------------------------------" + user.getRole());
			if (user.getRole() == null){
				user.setRole((Role) propDAO.getProp(PropertyType.ROLE, Constants.ADMIN_ROLE_ID));
				session.removeAttribute(Constants.KEY_USER);
				session.setAttribute(Constants.KEY_USER, user);
			}
			
		} else {
			User currentUser = (User) session.getAttribute(Constants.KEY_USER);
			
			if (user.getId() != currentUser.getId()) {
				throw new AccessDeniedException("You can't change properties of another user.");
			}

			user.setRole((Role) propDAO.getProp(PropertyType.ROLE, Constants.DEFAULT_ROLE_ID));
			session.removeAttribute(Constants.KEY_USER);
			session.setAttribute(Constants.KEY_USER, user);
		}
		
		user.setEnabled(true);
		userDAO.updateUser(user);
//		UserDetails details = ((UserDetails) principal);
		
//		String userPassword = ((UserDetails) principal).getPassword();
//		if (!userPassword.equals(user.getPassword())){
//			response = new ResponseEntity<String>("Bad password", HttpStatus.BAD_REQUEST);
//		}
		
		
//		List<GrantedAuthority> authorities = (List<GrantedAuthority>) details.getAuthorities();
		
//		if (authorities.contains(Constants.ROLE_ADMIN)) {
//			
//		} else if (authorities.contains(Constants.ROLE_ADMIN)) {
//			
//			response = new ResponseEntity<String>("Bad password", HttpStatus.BAD_REQUEST);
//		}
					
		response = new ResponseEntity<String>("", HttpStatus.OK); 
		return response;
	}
	
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={"oper=del", Constants.KEY_ID}, produces="application/json")
	public @ResponseBody String deleteUser(@RequestParam(Constants.KEY_ID) long id) throws DaoException {
		
		userDAO.deleteUser(id);
				
		return "";
	}
	
	/**
	   * Check if a role is present in the authorities of current user
	   * @param authorities all authorities assigned to current user
	   * @param role required authority
	   * @return true if role is present in list of authorities assigned to current user, false otherwise
	   */
	  private boolean isRolePresent(Collection<? extends GrantedAuthority> authorities, String role) {
	    boolean isRolePresent = false;
	    for (GrantedAuthority grantedAuthority : authorities) {
	      isRolePresent = grantedAuthority.getAuthority().equals(role);
	      if (isRolePresent) break;
	    }
	    return isRolePresent;
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
