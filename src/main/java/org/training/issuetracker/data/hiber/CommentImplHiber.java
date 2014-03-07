package org.training.issuetracker.data.hiber;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.training.issuetracker.domain.Comment;
import org.training.issuetracker.domain.DAO.CommentDAO;
import org.training.issuetracker.exceptions.DaoException;

public class CommentImplHiber implements CommentDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Comment> getCommentsList(long issueId) throws DaoException {
		List result = hibernateTemplate.find("from Comment where issueId=?", issueId);
		return Collections.checkedList(result, Comment.class);
	}

	@Override
	public Comment getComment(long id) throws DaoException {
		return hibernateTemplate.get(Comment.class, id);
	}

	@Override
	public long insertComment(Comment comment) throws DaoException {
		return (Long) hibernateTemplate.save(comment);
	}

	@Override
	public void updateComment(Comment comment) throws DaoException {
		hibernateTemplate.update(comment);
	}

	@Override
	public void deleteComment(long id) throws DaoException {
		hibernateTemplate.delete(hibernateTemplate.get(Comment.class, id));
	}

	@Override
	public void deleteIssueComments(long issueId) throws DaoException {
		hibernateTemplate.deleteAll(getCommentsList(issueId));
	}

}
