package org.training.issuetracker.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="RESOLUTIONS")
public class Resolution extends AbstractPersistentObj {

	public Resolution() { }

	public Resolution(long id, String name) {
		super(id, name);
	}
	
}
