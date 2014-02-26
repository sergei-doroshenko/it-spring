package org.training.issuetracker.domain.DAO;

import org.training.issuetracker.domain.*;

public enum PropertyType {
	STATUS(Status.class, new Status()), 
	RESOLUTION(Resolution.class, new Resolution()),
	PRIORITY(Priority.class, new Priority()),
	TYPE(Type.class, new Type()),
	ROLE(Role.class, new Role());
	
	private Class<?> propClass;
	private AbstractPersistentObj instanse;
	
	private PropertyType(Class<?> propClass, AbstractPersistentObj obj) {
		this.propClass = propClass;
		this.instanse = obj;
	}

	public Class<?> getPropClass() {
		return propClass;
	}

	public AbstractPersistentObj getInstanse() {
		return instanse;
	}
	
	public String getEntitiName () {
		return getPropClass().getCanonicalName();
	}
}
