package org.training.issuetracker.domain.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.exceptions.DaoException;

public interface IssueDAO {

	List <Issue> getIssueList (User user) throws DaoException;

	Map<Long, Issue> getIssuesMap() throws SQLException;

	Issue getIssue(long id) throws DaoException;
}
