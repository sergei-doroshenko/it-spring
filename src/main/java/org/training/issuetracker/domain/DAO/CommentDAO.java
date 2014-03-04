package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.Comment;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.exceptions.DaoException;

public interface CommentDAO {

	List<Comment> getCommentsList(long issueId) throws DaoException;
	
	Comment getComment(long id)  throws DaoException;

	long insertComment(Comment comment) throws DaoException;

	void updateComment(Comment comment) throws DaoException;
	
	void deleteComment(long id) throws DaoException;

}
