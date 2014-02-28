package org.training.issuetracker.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ROLES")
public class Role  extends AbstractPersistentObj{
	
	public Role() { }

	public Role(long id, String name) {
		super(id, name);
	}
	
	public Role(String name) {
		super(name);
	}
	
}
