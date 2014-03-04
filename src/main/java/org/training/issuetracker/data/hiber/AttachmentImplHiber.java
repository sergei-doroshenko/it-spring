package org.training.issuetracker.data.hiber;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.training.issuetracker.domain.Attachment;
import org.training.issuetracker.domain.DAO.AttachmentDAO;
import org.training.issuetracker.exceptions.DaoException;

public class AttachmentImplHiber implements AttachmentDAO {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<Attachment> getAttachmentsList(long issueId)
			throws DaoException {
		List result = hibernateTemplate.find("from Attachment where issueId=?", issueId);
		return Collections.checkedList(result, Attachment.class);
	}

	@Override
	public Attachment getAttchment(long id) throws DaoException {
		return hibernateTemplate.get(Attachment.class, id);
	}

	@Override
	public long insertAttachment(Attachment attchment) throws DaoException {
		return (Long) hibernateTemplate.save(attchment);
	}

	@Override
	public void deleteAttachment(long id) throws DaoException {
		hibernateTemplate.delete(hibernateTemplate.get(Attachment.class, id));
	}

}
