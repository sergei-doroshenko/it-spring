package org.training.issuetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.training.issuetracker.exceptions.DaoException;

@Controller
@RequestMapping("/views")
public class ViewDispatcher {
	
	@RequestMapping(value="/issues", method = RequestMethod.GET)
	public String issues (ModelMap model) throws DaoException {
		
		return "issues";
	}
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public String users (ModelMap model) throws DaoException {
		
		return "users";
	}
	
	@RequestMapping(value="/properties", method = RequestMethod.GET)
	public String properties (ModelMap model) throws DaoException {
		
		return "properties";
	}
}
