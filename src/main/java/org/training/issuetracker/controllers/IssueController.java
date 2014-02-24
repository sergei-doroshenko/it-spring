package org.training.issuetracker.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.JqGridData;

@Controller
@RequestMapping("/issue")
public class IssueController {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private IssueDAO issueDAO;
	
	@RequestMapping(value="/list", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String getIssueList (@RequestParam("page") int page, @RequestParam("rows") int rows) throws DaoException {
		
		List<Issue> issueList = issueDAO.getIssueList(null);
		logger.debug("Spring issue List = " + issueList);
		int records = issueList.size();
		int total = records/rows;
		JqGridData<Issue> data = new JqGridData<Issue>(total, page, records, issueList);
		String json = data.getJsonString();
		logger.debug("Spring issuelist json = " + json);
			
		return json;
	}
	
	@RequestMapping(method = RequestMethod.GET, params="id")
	public String getIssueById (@RequestParam(Constants.KEY_ID) long id, 
			@ModelAttribute(Constants.KEY_USER) User currentUser, HttpSession session) throws DaoException {
		
		if (currentUser.getId() > 0) {
			session.setAttribute(Constants.ISSUE, issueDAO.getIssueById(id));
		}
		
		return "edit-issue";
	}
	
	@ExceptionHandler(DaoException.class)
	public ResponseEntity<String> handleIOException(DaoException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
