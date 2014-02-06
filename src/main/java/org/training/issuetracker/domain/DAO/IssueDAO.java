package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.exceptions.DaoException;

public interface IssueDAO {

	List <Issue> getIssueList (User user) throws DaoException;

	Issue getIssue(long id) throws DaoException;

	boolean isId (long id) throws DaoException;

	long insertIssue(Issue issue) throws DaoException;

	long updateIssue (Issue issue) throws DaoException;
}
