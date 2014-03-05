package org.training.issuetracker.domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="BUILDS")
public class Build extends AbstractPersistentObj {
	
	@OneToOne()
	@JoinColumn(name="PROJECT_ID")
	private Project project;

	public Build() { }

	public Build(long id, String name, Project project) {
		super(id, name);
		this.project = project;
	}
	
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String toString() {
		JsonObject issueJson = Json.createObjectBuilder()
				.add("id", getId())
				.add("name", getName())
				.add("project", project.getName())
				.build();
		return issueJson.toString();
	}

}
