package org.training.issuetracker.domain;

import java.util.List;

public class Project extends AbstractPersistentObj {
	private String description;
	private List<ProjectBuild> buids;
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

	public List<ProjectBuild> getBuids() {
		return buids;
	}

	public void setBuids(List<ProjectBuild> buids) {
		this.buids = buids;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}
	
}
