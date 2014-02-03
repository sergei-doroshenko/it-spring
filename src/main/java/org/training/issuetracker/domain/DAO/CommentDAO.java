package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.Comment;
import org.training.issuetracker.exceptions.DaoException;

public interface CommentDAO {

	List<Comment> getCommentsList(long issueId) throws DaoException;

}
