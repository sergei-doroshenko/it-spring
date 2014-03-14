package org.training.issuetracker.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="STATUSES")
public class Status extends AbstractPersistentObj {
	
	public Status() { }

	public Status(long id, String name) {
		super(id, name);
	}
}
