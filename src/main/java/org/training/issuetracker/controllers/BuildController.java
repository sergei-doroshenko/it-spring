package org.training.issuetracker.controllers;

import java.util.List;

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
import org.training.issuetracker.domain.Build;
import org.training.issuetracker.domain.Project;
import org.training.issuetracker.domain.DAO.BuildDAO;
import org.training.issuetracker.domain.DAO.ProjectDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.JqGridData;
import org.training.issuetracker.utils.SearchFilterParams;

import flexjson.JSONSerializer;

@Controller
@RequestMapping("/build")
public class BuildController {
	
	@Autowired
	private BuildDAO buildDAO;
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@RequestMapping(method = RequestMethod.GET, params="_search", produces="application/json")
	public @ResponseBody String getBuildsList(SearchFilterParams params) throws DaoException {
		int records = buildDAO.getBuildsRecordsCount();
		
		List<Build> builds = buildDAO.getBuildsList(params);
		
		int total = (int) Math.ceil((double)records/(double) params.getRows());			
		JqGridData<Build> data = new JqGridData<>(total, params.getPage(), records, builds);
		String json = data.getJsonString();
		return json;
	}
	
	@RequestMapping(value="/project/{projectId}", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String getBuildsByProjectId (@PathVariable long projectId) throws DaoException {
		
		List<Build> builds = buildDAO.getProjectBuilds(projectId);
		String json = new JSONSerializer().exclude("*.class").deepSerialize(builds);
		return json;
	}
	 
	@RequestMapping(value="/add", method = RequestMethod.POST, params={Constants.KEY_NAME, 
			Constants.KEY_PROJECT_ID}, produces="application/json")
	public @ResponseBody String addBuild(@RequestParam(Constants.KEY_NAME) String name,  
			@RequestParam(Constants.KEY_PROJECT_ID) long projectId) throws DaoException {
		
		Build build = new Build();
		build.setName(name);
		Project project = projectDAO.getProject(projectId);
		build.setProject(project);
		
		buildDAO.insertBuild(build);
		
		return "";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={Constants.KEY_ID, Constants.KEY_NAME, 
			Constants.KEY_PROJECT_ID}, produces="application/json")
	public @ResponseBody String editBuild(@RequestParam(Constants.KEY_ID) long id, @RequestParam(Constants.KEY_NAME) String name, 
			@RequestParam(Constants.KEY_PROJECT_ID) long projectId) throws DaoException {
		
		Build build = new Build();
		build.setId(id);
		build.setName(name);
		Project project = projectDAO.getProject(projectId);
		build.setProject(project);
		buildDAO.updateBuild(build);
		
		return "";
	}
	
	@RequestMapping(value="/del", method = RequestMethod.POST, params={Constants.KEY_ID}, produces="application/json")
	public @ResponseBody String deleteBuild(@RequestParam(Constants.KEY_ID) long id) throws DaoException {
		
		buildDAO.deleteBuild(id);
		
		return "";
	}
	
	@ExceptionHandler(DaoException.class)
	public ResponseEntity<String> handleDaoException(DaoException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
