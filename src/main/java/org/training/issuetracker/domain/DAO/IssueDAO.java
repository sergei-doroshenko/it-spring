package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.SearchFilterParams;

public interface IssueDAO {
	
	int getIssueRecordsCount() throws DaoException;
	
	List <Issue> getIssueList (SearchFilterParams params) throws DaoException;

	Issue getIssueById(long id) throws DaoException;

	long insertIssue(Issue issue) throws DaoException;

	void updateIssue (Issue issue) throws DaoException;

	void deleteIssue (long id)  throws DaoException;

}
