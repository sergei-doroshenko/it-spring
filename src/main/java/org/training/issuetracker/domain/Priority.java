package org.training.issuetracker.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PRIORITIES")
public class Priority extends AbstractPersistentObj {

	public Priority() { }

	public Priority(long id, String name) {
		super(id, name);
	}
	
	
}
