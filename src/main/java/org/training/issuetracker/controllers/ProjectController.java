package org.training.issuetracker.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.training.issuetracker.constants.Constants;
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.domain.User;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.domain.DAO.UserDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.JqGridData;

@Controller
@RequestMapping("/project")
public class ProjectController {
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(method = RequestMethod.GET,  params="_search", produces="application/json; charset=utf-8; charset=utf-8; charset=utf-8")
	public @ResponseBody String getProjectsList(@RequestParam("page") int page, @RequestParam("rows") int rows) throws DaoException {
		
		List<Project> projects = projectDAO.getProjectsList();
		int records = projects.size();
		int total = records/rows;			
		JqGridData<Project> data = new JqGridData<>(total, page, records, projects);
		String json = data.getJsonString();
		return json;
	}
	
	/**This method return project options for select html block.
	 * @return html tags <select> with <options>
	 * @throws DaoException
	 */
	@RequestMapping(value="/options", method = RequestMethod.GET, produces="text/plain")
	public @ResponseBody String getProjectsOptions() throws DaoException {
		String options = "<select>";
		List<Project> projects = projectDAO.getProjectsList();
		for (Project project : projects) {
			options += "<option value=" + project.getId() + ">" + project.getName() + "</option>";
		}
		options += "</select>";
		return options;
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, params={Constants.KEY_NAME, 
			Constants.KEY_DESCRIPTION, Constants.KEY_MANAGER_ID}, produces="application/json; charset=utf-8; charset=utf-8; charset=utf-8")
	public @ResponseBody String addProject(@RequestParam(Constants.KEY_NAME) String name, @RequestParam(Constants.KEY_DESCRIPTION) String description, 
			@RequestParam(Constants.KEY_MANAGER_ID) long managerId) throws DaoException {
		
		Project project = new Project();
		project.setName(name);
		project.setDescription(description);
		User manager = userDAO.getUser(managerId);
		project.setManager(manager);
		projectDAO.insertProject(project);
		
		return "";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={Constants.KEY_ID, Constants.KEY_NAME, 
			Constants.KEY_DESCRIPTION, Constants.KEY_MANAGER_ID}, produces="application/json; charset=utf-8; charset=utf-8; charset=utf-8")
	public @ResponseBody String editProject(@RequestParam(Constants.KEY_ID) long id, @RequestParam(Constants.KEY_NAME) String name, 
			@RequestParam(Constants.KEY_DESCRIPTION) String description, @RequestParam(Constants.KEY_MANAGER_ID) long managerId) throws DaoException {
		
		Project project = new Project();
		project.setId(id);
		project.setName(name);
		project.setDescription(description);
		User manager = userDAO.getUser(managerId);
		project.setManager(manager);
		projectDAO.updateProject(project);
		
		return "";
	}
	
	@RequestMapping(value="/del", method = RequestMethod.POST, params={Constants.KEY_ID}, produces="application/json; charset=utf-8; charset=utf-8; charset=utf-8")
	public @ResponseBody String deleteProject(@RequestParam(Constants.KEY_ID) long id) throws DaoException {
		
		projectDAO.deleteProject(id);
		
		return "";
	}
	
	@ExceptionHandler(DaoException.class)
	public ResponseEntity<String> handleDaoException(DaoException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
