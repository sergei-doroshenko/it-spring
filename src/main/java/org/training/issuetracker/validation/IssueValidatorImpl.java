package org.training.issuetracker.validation;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Issue;

@Component
public class IssueValidatorImpl implements IssueValidator {
	
	@Autowired
	private MessageSource messageSource;
	
	public IssueValidatorImpl() {}

	@Override
	public String validate(Issue issue) {
		String message = null;
		
		Locale locale = LocaleContextHolder.getLocale();
		String country = locale.getCountry();
		String language = locale.getLanguage();
		
		if (issue.getStatus().getName().equals(Constants.STATUS_ASSIGNED) 
				&& issue.getAssignee() == null) {
			//message = "If status is ASSIGNED - assignee can't be empty.";
			message = messageSource.getMessage("issue.err.assignee.empty", null, locale);
			
		}
		return message;
	}
	
}
