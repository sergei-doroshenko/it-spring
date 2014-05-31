package org.training.issuetracker.utils;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

public class AjaxResponse {
	private Model model; //form attribute
	private HttpStatus status;  // OK or ERROR
    private String description; // message description such as error message
	
    public AjaxResponse() {}

	public AjaxResponse(Model model, HttpStatus status, String description) {
		super();
		this.model = model;
		this.status = status;
		this.description = description;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
 
}
