package org.training.issuetracker.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.training.issuetracker.domain.Type;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.exceptions.DaoException;

public class TypeConverter implements Converter<String, Type> {
	
	@Autowired
	private PropDAO propDAO;
	
	@Override
	public Type convert(String text) {
		long id = Long.parseLong(text);
		
		Type type = null;
		
		try {
			 type = (Type) propDAO.getProp(PropertyType.TYPE, id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return type;
	}

}
