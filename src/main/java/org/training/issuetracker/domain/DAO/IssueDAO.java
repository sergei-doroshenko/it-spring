package org.training.issuetracker.domain.DAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.exceptions.DaoException;

public interface IssueDAO {
	Map <Long, Issue> getIssue () throws DaoException;
	
	List <Issue> getIssueList () throws DaoException;
	
	Map<Long, Issue> getIssuesMap() throws SQLException;
}
