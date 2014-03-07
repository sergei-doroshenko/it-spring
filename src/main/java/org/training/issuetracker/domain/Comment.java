package org.training.issuetracker.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMMENTS")
public class Comment {
	
	@Id
    @Column(name="ID")
    @GeneratedValue
	private long id;
	
	@Column(name="ISSUE_ID")
	private long issueId;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@OneToOne()
	@JoinColumn(name="CREATE_BY")
	private User createBy;
	
	@Column(name="COMMENT")
	private String comment;

	public Comment() { }

	public Comment(long issueId, Date createDate, User createBy, String comment) {
		super();
		this.issueId = issueId;
		this.createDate = createDate;
		this.createBy = createBy;
		this.comment = comment;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
