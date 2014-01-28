package org.training.issuetracker.domain;

public class Build extends AbstractPersistentObj {
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

}
