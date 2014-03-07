package org.training.issuetracker.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.training.issuetracker.domain.Attachment;
import org.training.issuetracker.domain.DAO.AttachmentDAO;
import org.training.issuetracker.exceptions.DaoException;

public class AttachmentConverter implements Converter<String, Attachment> {

	@Autowired
	private AttachmentDAO attachmentDAO;
	
	@Override
	public Attachment convert(String text) {
		long id = Long.parseLong(text);
		
		Attachment attachment = null;
		if (id >= 0) {
			try {
				 attachment = (Attachment) attachmentDAO.getAttchment(id);
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		
		return attachment;
	}

}
