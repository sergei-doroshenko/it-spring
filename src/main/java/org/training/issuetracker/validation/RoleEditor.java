package org.training.issuetracker.validation;

import java.beans.PropertyEditorSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Role;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.exceptions.DaoException;

@Component
public class RoleEditor extends PropertyEditorSupport {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private PropDAO propDAO;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		long roleId = Constants.DEFAULT_ROLE_ID;

		if (!text.isEmpty()) {
			roleId = Long.parseLong(text);
		}

		Role tempRole = null;
		try {
			Object obj = propDAO.getProp(PropertyType.ROLE, roleId);
			logger.debug("Object from editor ------------------------------------ " + obj);
			tempRole = (Role) propDAO.getProp(PropertyType.ROLE, roleId);
		} catch (DaoException | NullPointerException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("No such role!");
		}
		
		setValue(tempRole);
	}
}
