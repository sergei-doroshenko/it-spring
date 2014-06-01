package org.training.issuetracker.validation;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Issue;

@Component
public class IssueValidator implements Validator {
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	public boolean supports(Class<?> cl) {
		
		return cl.equals(Issue.class);
	}

	@Override
	public void validate(Object obj, Errors err) {
		Locale locale = LocaleContextHolder.getLocale();
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "type", "issue.err.mandatoryfield.empty", 
				messageSource.getMessage("issue.err.mandatoryfield.empty", 
						new Object[] {messageSource.getMessage("page.issue.type", null, locale)}, locale));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "priority", "issue.err.mandatoryfield.empty", 
				messageSource.getMessage("issue.err.mandatoryfield.empty", 
						new Object[] {messageSource.getMessage("page.issue.priority", null, locale)}, locale));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "status", "issue.err.mandatoryfield.empty", 
				messageSource.getMessage("issue.err.mandatoryfield.empty", 
						new Object[] {messageSource.getMessage("page.issue.status", null, locale)}, locale));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "project", "issue.err.mandatoryfield.empty", 
				messageSource.getMessage("issue.err.mandatoryfield.empty", 
						new Object[] {messageSource.getMessage("page.issue.project", null, locale)}, locale));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "build", "issue.err.mandatoryfield.empty", 
				messageSource.getMessage("issue.err.mandatoryfield.empty", 
						new Object[] {messageSource.getMessage("page.issue.build", null, locale)}, locale));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "summary", "issue.err.mandatoryfield.empty", 
				messageSource.getMessage("issue.err.mandatoryfield.empty", 
						new Object[] {messageSource.getMessage("page.issue.summary", null, locale)}, locale));
		
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "description", "issue.err.mandatoryfield.empty", 
				messageSource.getMessage("issue.err.mandatoryfield.empty", 
						new Object[] {messageSource.getMessage("page.issue.description", null, locale)}, locale));
		
		Issue issue = (Issue) obj;
		
		if (issue.getStatus().getName().equals(Constants.STATUS_ASSIGNED) && issue.getAssignee() == null) {	
			err.rejectValue("assignee", "issue.err.assignee.empty",  messageSource.getMessage("issue.err.assignee.empty", null, locale));
		}
		
		if (issue.getStatus().getName().equals(Constants.STATUS_CLOSED) && issue.getResolution() == null) {	
			err.rejectValue("resolution", "issue.err.resolution.empty",  messageSource.getMessage("issue.err.resolution.empty", null, locale));
		}
	}

}
