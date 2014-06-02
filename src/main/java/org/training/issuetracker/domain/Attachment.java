package org.training.issuetracker.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.training.issuetracker.constants.Constants;

@Entity
@Table(name="ATTACHMENTS")
public class Attachment {
	
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
	
	@Column(name="FILE_NAME")
	private String fileName;

	public Attachment() { }

	public Attachment(long issueId, Date createDate, User createBy, String fileName) {
		super();
		this.issueId = issueId;
		this.createDate = createDate;
		this.createBy = createBy;
		this.fileName = fileName;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public File getFile() throws FileNotFoundException {
		String path = Constants.getRealPath() + Constants.URL_UPLOAD_DIR + File.separator
				+  issueId + File.separator + fileName;
		
		File file = new File(path);
		
		if(!file.exists()) {
			throw new FileNotFoundException ("File doesn't exists on server.");
		}
		
		return file;
	}
	
	public File getFolder() throws FileNotFoundException {
		String path = Constants.getRealPath() + Constants.URL_UPLOAD_DIR + File.separator
				+  issueId;
		
		File file = new File(path);
		
		if(!file.exists()) {
			throw new FileNotFoundException ("Folder doesn't exists on server.");
		}
		
		return file;
	}
}
