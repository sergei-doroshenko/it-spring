package org.training.issuetracker.validation;

import org.training.issuetracker.domain.Issue;

public interface IssueValidator {
	String validate(Issue issue);
}
