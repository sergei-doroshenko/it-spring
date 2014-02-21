package org.training.issuetracker.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TYPES")
public class Type extends AbstractPersistentObj {

	public Type() {	}

	public Type(long id, String name) {
		super(id, name);
	}
	
}
