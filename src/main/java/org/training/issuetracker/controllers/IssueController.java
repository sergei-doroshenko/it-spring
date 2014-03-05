package org.training.issuetracker.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.DAO.AttachmentDAO;
import org.training.issuetracker.domain.DAO.CommentDAO;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.JqGridData;
import org.training.issuetracker.utils.SearchFilterParams;

@Controller
@RequestMapping("/issue")
public class IssueController {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private IssueDAO issueDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private AttachmentDAO attachmentDAO;
	
	@RequestMapping(value="/list", method = RequestMethod.GET, params="_search", produces="application/json")
	public @ResponseBody String getIssueList (SearchFilterParams params) throws DaoException {
		
		int records = issueDAO.getIssueRecordsCount();
		
		List<Issue> issues = issueDAO.getIssueList(params);
		
		int total = (int) Math.ceil((double)records/(double) params.getRows());
		
		JqGridData<Issue> data = new JqGridData<Issue>(total, params.getPage(), records, issues);
		String json = data.getJsonString();
			
		return json;
	}
	
	@RequestMapping(method = RequestMethod.GET, params="id")
	public String viewIssue (@RequestParam(Constants.KEY_ID) long id, ModelMap model) throws DaoException {
		
		model.addAttribute(Constants.ISSUE, issueDAO.getIssueById(id));
		model.addAttribute(Constants.COMMENTS, commentDAO.getCommentsList(id));
		model.addAttribute(Constants.ATTACHMENTS, attachmentDAO.getAttachmentsList(id));
		return "view-issue";
	}
	
	@RequestMapping(value="/edit", params="id", method = RequestMethod.GET)
	public String editIssue (@RequestParam long id, ModelMap model) throws DaoException {
		
		model.addAttribute(Constants.ISSUE, issueDAO.getIssueById(id));
		model.addAttribute(Constants.COMMENTS, commentDAO.getCommentsList(id));
		model.addAttribute(Constants.ATTACHMENTS, attachmentDAO.getAttachmentsList(id));
		return "edit-issue";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public @ResponseBody String saveIssue (@RequestParam long id, ModelMap model) throws DaoException {
		
//		long id = issue.getId();
//		if (id == 0) {
//			id = issueDAO.insertIssue(issue); 
//		} else {
//			issueDAO.updateIssue(issue);
//		}	
//		model.addAttribute(Constants.ISSUE, issue);
		
		return "";
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteIssue (@PathVariable long id, ModelMap model) throws DaoException {
		issueDAO.deleteIssue(id);
		return "edit-issue";
	}
	
	@ExceptionHandler(DaoException.class)
	public ResponseEntity<String> handleIOException(DaoException ex) {
		ex.printStackTrace();
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
