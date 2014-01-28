package org.training.issuetracker.domain;

import java.sql.Date;

public class Attachment extends AbstractPersistentObj {
	private long issueId;
	private Date createDate;
	private User createBy;
	private String url;

	public Attachment() { }

	public Attachment(long issueId, Date createDate, User createBy, String url) {
		super();
		this.issueId = issueId;
		this.createDate = createDate;
		this.createBy = createBy;
		this.url = url;
	}

	public long getIssueId() {
		return issueId;
	}

	public void setIssueId(long issueId) {
		this.issueId = issueId;
	}

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
