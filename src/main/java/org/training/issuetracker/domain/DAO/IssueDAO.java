package org.training.issuetracker.domain.DAO;

import java.util.Map;

import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.exceptions.DaoException;

public interface IssueDAO {
	Map <Long, Issue> getIssue () throws DaoException;
}
