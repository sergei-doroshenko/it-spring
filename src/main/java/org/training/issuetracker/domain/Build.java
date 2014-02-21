package org.training.issuetracker.domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="BUILDS")
public class Build extends AbstractPersistentObj {
	
	@Column(name="PROJECT_ID")
	private long projectId;

	public Build() { }

	public Build(long id, String name, long projectId) {
		super(id, name);
		this.projectId = projectId;
	}
	
	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		JsonObject issueJson = Json.createObjectBuilder()
				.add("id", getId())
				.add("name", getName())
				.add("projectId", projectId)
				.build();
		return issueJson.toString();
	}

}
