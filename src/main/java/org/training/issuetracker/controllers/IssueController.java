package org.training.issuetracker.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	public @ResponseBody String getIssueList (@RequestParam("rows") int rows, @RequestParam("page") int page,
			@RequestParam("sidx") String sidx, @RequestParam("sord") String sord) throws DaoException {
		
		int records = issueDAO.getIssueRecordsCount();
		
		List<Issue> issues = issueDAO.getIssueList(null, page, rows, sidx, sord);
		
		int total = (int) Math.ceil((double)records/(double)rows);
		
		JqGridData<Issue> data = new JqGridData<Issue>(total, page, records, issues);
		String json = data.getJsonString();
			
		return json;
	}
	
	@RequestMapping(method = RequestMethod.GET, params="id")
	public String viewIssue (@RequestParam(Constants.KEY_ID) long id, ModelMap model) throws DaoException {
		
		model.addAttribute(Constants.ISSUE, issueDAO.getIssueById(id));
		return "view-issue";
	}
	
	@ExceptionHandler(DaoException.class)
	public ResponseEntity<String> handleIOException(DaoException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
