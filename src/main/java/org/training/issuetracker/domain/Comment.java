package org.training.issuetracker.domain;

import java.sql.Date;

public class Comment extends AbstractPersistentObj {
	private Date createDate;
	private User createBy;
	private String comment;
	
	public Comment() { }

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
