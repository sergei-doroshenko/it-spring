package org.training.issuetracker.exceptions;

public class ParameterNotFoundException extends Exception {
private static final long serialVersionUID = 1L;
	
	public ParameterNotFoundException(String message) {
		super(message);
	}
}
