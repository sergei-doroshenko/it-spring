package org.training.issuetracker.domain;

public interface PersistentObj {
	
	long getId();
	void setId(long id);
	
	String getName();
	void setName(String name);
}
