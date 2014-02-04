package org.training.issuetracker.domain;

import java.sql.Date;

import javax.json.Json;
import javax.json.JsonObject;

import com.google.gson.Gson;

public class Issue extends AbstractPersistentObj {
	private Date createDate;
	private User createBy;
	private Date modifyDate;
	private User modifyBy;
	private String summary;
	private String description;
	private Status status;
	private Resolution resolution;
	private Type type;
	private Priority priority;
	private Project project;
	private Build build;
	private User assignee;

	public Issue() { }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public User getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(User modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Resolution getResolution() {
		return resolution;
	}

	public void setResolution(Resolution resolution) {
		this.resolution = resolution;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Build getBuild() {
		return build;
	}

	public void setBuild(Build build) {
		this.build = build;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

//	public JsonObject toJson () {
//		JsonObject issueJson = Json.createObjectBuilder()
//						.add("id", getId())
//						.add("cell", Json.createArrayBuilder()
//							.add(getId()).add(getCreateDate().toString())
//							.add(getCreateBy().toString())
//							.add(getModifyDate().toString())
//							.add(getModifyBy().toString()).add(getSummary())
//							.add(getStatus().getName())
//							.add(
//								(null == getResolution() ? "UNRESOLVED" : getResolution().getName())
//							)
//							.add(getType().getName()).add(getPriority().getName())
//							.add(getProject().getName())
//							.add(getAssignee().toString())
//						).build();
//		return issueJson;
//	}

	public JsonObject toJsonForTable () {
		JsonObject issueJson = Json.createObjectBuilder()
						.add("id", getId())
						.add("cell", Json.createArrayBuilder()
							.add(getId())
							.add(getPriority().getName())
							.add(getAssignee().toString())
							.add(getType().getName())
							.add(getStatus().getName())
							.add(getSummary())
						).build();
		return issueJson;
	}

	public JsonObject toJsonObj () {
		JsonObject issueJson = Json.createObjectBuilder()
							.add("id", getId())
							.add("createdate", getCreateDate().toString())
							.add("createby", getCreateBy().toString())
							.add("modifydate", getModifyDate().toString())
							.add("modifyby", getModifyBy().toString())
							.add("summary", getSummary())
							.add("description", getDescription())
							.add("status", getStatus().getName())
							.add("resolution",
								(null == getResolution() ? "UNRESOLVED" : getResolution().getName())
							)
							.add("type", getType().getName())
							.add("priority", getPriority().getName())
							.add("project", getBuild().getProjectId())
							.add("projectbuild", getBuild().getName())
							.add("assignee", getAssignee().toString())
						.build();
		return issueJson;
	}

	@Override
	public String toString() {
		return "Issue [id=" + super.getId() + ", name=" + super.getName()
				+ ", createDate=" +  createDate + ", createBy=" + createBy.getFirstName()
				+ ", modifyDate=" + modifyDate + ", modifyBy=" + modifyBy.getFirstName()
				+ ", summary=" + summary + ", description=" + description
				+ ", status=" + status.getName() + ", resolution="
				+ (null == resolution ? "UNRESOLVED" : resolution.getName())
				+ ", type" + type.getName()
				+ ", priority=" + priority.getId() + ", project=" + getBuild().getProjectId()
				+ ", assignee=" + assignee.getFirstName() + "]";
	}


}
