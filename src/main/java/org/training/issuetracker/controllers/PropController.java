package org.training.issuetracker.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.issuetracker.data.db.PropImplDB.PropertyType;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.Issue;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.JqGridData;

@Controller
@RequestMapping("/prop")
public class PropController {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private PropDAO propDAO;
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String getPropList (@RequestParam("page") int page, 
			@RequestParam("type") String type) throws DaoException {
		
		PropertyType propType = PropertyType.valueOf(type);
		List<AbstractPersistentObj> propList = propDAO.getPropList(propType);
		
		int total = propList.size();
		int records = propList.size();
		JqGridData<AbstractPersistentObj> data = new JqGridData<AbstractPersistentObj>(total, page, records, propList);
		String json = data.getJsonString();
			
		return json;
	}

}
