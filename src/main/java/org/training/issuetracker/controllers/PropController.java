package org.training.issuetracker.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.AbstractPersistentObj;
import org.training.issuetracker.domain.DAO.PropDAO;
import org.training.issuetracker.domain.DAO.PropertyType;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.JqGridData;
import org.training.issuetracker.utils.SearchFilterParams;

@Controller
@RequestMapping("/prop")
public class PropController {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private PropDAO propDAO;
	
	@RequestMapping(method = RequestMethod.GET, params="_search", produces="application/json")
	public @ResponseBody String getPropList (SearchFilterParams params,
			@RequestParam("type") String type) throws DaoException {
		
		PropertyType propType = PropertyType.valueOf(type);
		int records = propDAO.getPropRecordsCount(propType);
		
		List<AbstractPersistentObj> propList = propDAO.getPropList(propType, params);
		
		int total = (int) Math.ceil((double)records/(double) params.getRows());
		
		JqGridData<AbstractPersistentObj> data = new JqGridData<AbstractPersistentObj>(total, params.getPage(), records, propList);
		String json = data.getJsonString();
			
		return json;
	}
	
	/**This method return project options for select html block.
	 * @return html tags <select> with <options>
	 * @throws DaoException
	 */
	@RequestMapping(value="/options/{type}", method = RequestMethod.GET, produces="text/plain")
	public @ResponseBody String getPropOptions(@PathVariable String type) throws DaoException {
		PropertyType propType = PropertyType.valueOf(type);
		String options = "<select>";
		List<AbstractPersistentObj> properties = propDAO.getPropList(propType);
		for (AbstractPersistentObj property : properties) {
			options += "<option value=" + property.getId() + ">" + property.getName() + "</option>";
		}
		options += "</select>";
		return options;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, params={Constants.KEY_NAME}, produces="application/json")
	public @ResponseBody String addProp(@RequestParam(Constants.KEY_NAME) String name, 
			@RequestParam("type") String type) throws DaoException {
		
		PropertyType propType = PropertyType.valueOf(type);
		AbstractPersistentObj prop = propType.getInstanse();
		prop.setName(name);
		propDAO.insertProp(PropertyType.valueOf(type), prop);
				
		return "";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={Constants.KEY_ID, 
			Constants.KEY_NAME}, produces="application/json")
	public @ResponseBody String editProject(@RequestParam(Constants.KEY_ID) long id, 
			@RequestParam(Constants.KEY_NAME) String name, @RequestParam("type") String type) throws DaoException {
		
		PropertyType propType = PropertyType.valueOf(type);
		AbstractPersistentObj prop = propType.getInstanse();
		prop.setId(id);
		prop.setName(name);
		propDAO.updateProp(propType, prop);
		
		return "";
	}
	
	@RequestMapping(value="/del", method = RequestMethod.POST, params={Constants.KEY_ID}, produces="application/json")
	public @ResponseBody String deleteProject(@RequestParam(Constants.KEY_ID) long id, @RequestParam("type") String type) throws DaoException {
		
		propDAO.deleteProp(PropertyType.valueOf(type), id);
		
		return "";
	}
	
	@ExceptionHandler(DaoException.class)
	public ResponseEntity<String> handleDaoException(DaoException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
