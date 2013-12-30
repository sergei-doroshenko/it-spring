package org.training.issuetracker.domain;

import java.sql.Date;

public class Attachment extends AbstractPersistentObj {
	private Date createDate;
	private User createBy;
	private String url;
	
	public Attachment() { }

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
