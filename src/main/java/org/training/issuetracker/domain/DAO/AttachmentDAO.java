package org.training.issuetracker.domain.DAO;

import java.util.List;

import org.training.issuetracker.domain.Attachment;
import org.training.issuetracker.exceptions.DaoException;

public interface AttachmentDAO {

	List<Attachment> getAttachmentsList(long issueId) throws DaoException;

	void addAttchment(Attachment attchment) throws DaoException;

	Attachment getAttchment(long attchmentId) throws DaoException;

}
