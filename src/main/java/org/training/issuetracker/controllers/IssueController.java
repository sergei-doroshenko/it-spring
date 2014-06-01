package org.training.issuetracker.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.Status;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.AttachmentDAO;
import org.training.issuetracker.domain.DAO.BuildDAO;
import org.training.issuetracker.domain.DAO.CommentDAO;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.AjaxResponse;
import org.training.issuetracker.utils.JqGridData;
import org.training.issuetracker.utils.SearchFilterParams;
import org.training.issuetracker.validation.IssueValidator;

import flexjson.JSONSerializer;

@Controller
@RequestMapping("/issue")
public class IssueController {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private IssueDAO issueDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private PropDAO propDAO;
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@Autowired
	private BuildDAO buildDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private AttachmentDAO attachmentDAO;
	
	@Autowired
	private IssueValidator issueValidator;
	
//	@Autowired
//	private ConversionService conversionService;
	
	@InitBinder("issue") 
	private void initBinder(WebDataBinder binder) { 
		binder.setValidator(issueValidator);
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET, params="_search", produces="application/json; charset=utf-8;")
	public @ResponseBody String getIssueList (SearchFilterParams params) throws DaoException {
		
		int records = issueDAO.getIssueRecordsCount();
		
		List<Issue> issues = issueDAO.getIssueList(params);
		
		int total = (int) Math.ceil((double)records/(double) params.getRows());
		
		JqGridData<Issue> data = new JqGridData<Issue>(total, params.getPage(), records, issues);
		String json = data.getJsonString();
			
		return json;
	}
	
	@RequestMapping(value="/view", method = RequestMethod.GET, params="id")
	public String viewIssue (@RequestParam(Constants.KEY_ID) long id, ModelMap model) throws DaoException {
		
		model.addAttribute(Constants.ISSUE, issueDAO.getIssueById(id));
		model.addAttribute(Constants.COMMENTS, commentDAO.getCommentsList(id));
		model.addAttribute(Constants.ATTACHMENTS, attachmentDAO.getAttachmentsList(id));
		
		return "view-issue";
	}
	
//	@PreAuthorize("hasAuthority('USER,ADMINISTRATOR')")
//	@PreAuthorize("hasAnyRole('USER','ADMINISTRATOR')")
	@RequestMapping(value="/new", method = RequestMethod.GET)
	public String newIssue (ModelMap model) throws DaoException {
		
		model.addAttribute(Constants.PROJECTS, projectDAO.getProjectsList());
		model.addAttribute(Constants.TYPES, propDAO.getPropList(PropertyType.TYPE));
		model.addAttribute(Constants.PRIORITIES, propDAO.getPropList(PropertyType.PRIORITY));
		model.addAttribute(Constants.RESOLUTIONS, propDAO.getPropList(PropertyType.RESOLUTION));
		model.addAttribute(Constants.STATUSES, propDAO.getPropList(PropertyType.STATUS));
		model.addAttribute(Constants.ASSIGNEES, userDAO.getUsersList());
		
		return "new-issue";
	}
	
	@RequestMapping(value="/edit", params="id", method = RequestMethod.GET)
	public String editIssue (@RequestParam long id, ModelMap model) throws DaoException {
		
		Issue issue = issueDAO.getIssueById(id);
		model.addAttribute(Constants.ISSUE, issue);
		model.addAttribute(Constants.PROJECTS, projectDAO.getProjectsList());
		long projectId =  issue.getProject().getId();
		model.addAttribute(Constants.BUILDS, buildDAO.getProjectBuilds(projectId));
		model.addAttribute(Constants.TYPES, propDAO.getPropList(PropertyType.TYPE));
		model.addAttribute(Constants.PRIORITIES, propDAO.getPropList(PropertyType.PRIORITY));
		model.addAttribute(Constants.RESOLUTIONS, propDAO.getPropList(PropertyType.RESOLUTION));
		model.addAttribute(Constants.STATUSES, propDAO.getPropList(PropertyType.STATUS));
		model.addAttribute(Constants.ASSIGNEES, userDAO.getUsersList());
		model.addAttribute(Constants.COMMENTS, commentDAO.getCommentsList(id));
		model.addAttribute(Constants.ATTACHMENTS, attachmentDAO.getAttachmentsList(id));
		
		return "edit-issue";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST, produces="application/json; charset=utf-8;")
	public ResponseEntity<String> updateIssue (@Valid Issue issue, BindingResult bindingResult,
			HttpSession session) throws DaoException {
		
		ResponseEntity<String> responseEntity = checkBindingResult(bindingResult);
		if (responseEntity != null) {
			return responseEntity;
		}
		
		long id = issue.getId();
		Issue oldIssue = (Issue) issueDAO.getIssueById(id);
		issue.setCreateDate(oldIssue.getCreateDate());
		issue.setCreateBy(oldIssue.getCreateBy());
		issue.setModifyDate(new Date(Calendar.getInstance().getTimeInMillis()));
		User currentUser = (User) session.getAttribute(Constants.KEY_USER);
		issue.setModifyBy(currentUser);
		issueDAO.updateIssue(issue);
		
		return new ResponseEntity<String>("/issuetracker/issue/view?id=" + id, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces="application/json; charset=utf-8;")
	public ResponseEntity<String> addIssue (@Valid Issue issue, BindingResult bindingResult, 
			HttpSession session, HttpServletResponse response) throws DaoException {
		
		ResponseEntity<String> responseEntity = checkBindingResult(bindingResult);
		if (responseEntity != null) {
			return responseEntity;
		}
		
		issue.setCreateDate(new Date(Calendar.getInstance().getTimeInMillis()));
		User currentUser = (User) session.getAttribute(Constants.KEY_USER);
		issue.setCreateBy(currentUser);
		
		long id = issueDAO.insertIssue(issue);
		return new ResponseEntity<String>("/issuetracker/issue/view?id=" + id, HttpStatus.OK);
	}

	private ResponseEntity<String> checkBindingResult(BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()){
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			List<FieldError> fieldValidationErrors = new ArrayList<>();
			
			for (FieldError fieldError : fieldErrors) {
				if (!fieldError.isBindingFailure()) {
					fieldValidationErrors.add(fieldError);
				}
			}
			
			String json = new JSONSerializer().exclude("*.class", "bindingFailure", "code", "objectName", "rejectedValue")
					.serialize(fieldValidationErrors);
			return new ResponseEntity<String>(json, HttpStatus.BAD_REQUEST);
		}
		
		return null;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteIssue (@PathVariable long id, ModelMap model) throws DaoException, IOException {
		
		attachmentDAO.deleteIssueAttachments(id);
		String uploadFilePath = Constants.getRealPath() + File.separator
        		+ Constants.URL_UPLOAD_DIR + File.separator + id;
		
		File fileUploadDir = new File(uploadFilePath);

		FileUtils.deleteDirectory(fileUploadDir);
		commentDAO.deleteIssueComments(id);
		
		issueDAO.deleteIssue(id);
		return "/issuetracker/index.jsp";
	}
	
	/*@ExceptionHandler(BindException.class)
	public ResponseEntity<String> handleBindException(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}*/
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> handleADException(AccessDeniedException ex) {
		ex.printStackTrace();
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(DaoException.class)
	public ResponseEntity<String> handleIOException(DaoException ex) {
		ex.printStackTrace();
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
