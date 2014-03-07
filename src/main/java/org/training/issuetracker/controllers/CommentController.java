package org.training.issuetracker.controllers;

import java.sql.Date;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Comment;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.CommentDAO;
import org.training.issuetracker.domain.DAO.IssueDAO;
import org.training.issuetracker.exceptions.DaoException;

@Controller
@RequestMapping("/comment")
public class CommentController {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private IssueDAO issueDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@RequestMapping(value="/{id}", method = RequestMethod.POST)
	public @ResponseBody String addComment (@PathVariable long id, @RequestParam String text, HttpSession session) throws DaoException {
		
		Issue issue = (Issue) issueDAO.getIssueById(id);
        
		if (issue == null) {
			return "";
		}
		
		User user = (User) session.getAttribute(Constants.KEY_USER);
		
		Comment comment = new Comment();
		comment.setIssueId(id);
		comment.setCreateDate(new Date(Calendar.getInstance().getTimeInMillis()));
		comment.setCreateBy(user);
		comment.setComment(text);
		commentDAO.insertComment(comment);
		
		return "/issuetracker/issue/edit?id=" + id;
	}
	
	@RequestMapping(value="/del/{issueId}/{commentId}", method = RequestMethod.GET)
	public RedirectView removeComment (@PathVariable long issueId,
			@PathVariable long commentId) throws DaoException {
		
		commentDAO.deleteComment(commentId);
		return new RedirectView("/issuetracker/issue/edit?id=" + issueId, false);
	}
	
}
