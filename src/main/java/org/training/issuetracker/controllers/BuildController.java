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
import org.training.issuetracker.domain.DAO.BuildDAO;
import org.training.issuetracker.exceptions.DaoException;
import org.training.issuetracker.utils.JqGridData;

import flexjson.JSONSerializer;

@Controller
@RequestMapping("/build")
public class BuildController {
	
	@Autowired
	private BuildDAO buildDAO;
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String getBuildsList(@RequestParam("page") int page, @RequestParam("rows") int rows) throws DaoException {
		
		List<Build> builds = buildDAO.getBuildsList();
		int records = builds.size();
		int total = records/rows;			
		JqGridData<Build> data = new JqGridData<>(total, page, records, builds);
		String json = data.getJsonString();
		return json;
	}
	
	@RequestMapping(value="/project/{projectId}", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String getBuildsByProjectId (@PathVariable long projectId) throws DaoException {
		
		List<Build> builds = buildDAO.getProjectBuilds(projectId);
		String json = new JSONSerializer().exclude("*.class").deepSerialize(builds);
		return json;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={"oper=add", Constants.KEY_NAME, 
			Constants.KEY_PROJECT_ID}, produces="application/json")
	public @ResponseBody String addBuild(@RequestParam(Constants.KEY_NAME) String name,  
			@RequestParam(Constants.KEY_PROJECT_ID) long projectId) throws DaoException {
		
		Build build = new Build();
		build.setName(name);
		build.setProjectId(projectId);
		
		buildDAO.insertBuild(build);
		
		return "";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={"oper=edit", Constants.KEY_ID, Constants.KEY_NAME, 
			Constants.KEY_PROJECT_ID}, produces="application/json")
	public @ResponseBody String editBuild(@RequestParam(Constants.KEY_ID) long id, @RequestParam(Constants.KEY_NAME) String name, 
			@RequestParam(Constants.KEY_PROJECT_ID) long projectId) throws DaoException {
		
		Build build = new Build();
		build.setId(id);
		build.setName(name);
		build.setProjectId(projectId);
		buildDAO.updateBuild(build);
		
		return "";
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, params={"oper=del", Constants.KEY_ID}, produces="application/json")
	public @ResponseBody String deleteBuild(@RequestParam(Constants.KEY_ID) long id) throws DaoException {
		
		buildDAO.deleteBuild(id);
		
		return "";
	}
	
	@ExceptionHandler(DaoException.class)
	public ResponseEntity<String> handleDaoException(DaoException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}