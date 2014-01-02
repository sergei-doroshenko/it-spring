package org.training.issuetracker.domain;

import java.util.ArrayList;
import java.util.List;

public class Project extends AbstractPersistentObj {
	private String description;
	private List<String> builds = new ArrayList<String>();
	private User manager;
	
	public Project() { }

	public Project(long id, String name) {
		super(id, name);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getBuilds() {
		return builds;
	}

	public void setBuilds(List<String> builds) {
		this.builds = builds;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Project [" + description + ", builds=" + builds
				+ ", manager=" + manager.getFirstName() + manager.getLastName() + "]";
	}
	
}
