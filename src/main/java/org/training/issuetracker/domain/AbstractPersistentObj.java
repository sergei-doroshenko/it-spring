package org.training.issuetracker.domain;

public abstract class AbstractPersistentObj implements PersistentObj {
	private long id;
	private String name;
	
	public AbstractPersistentObj() { }

	public AbstractPersistentObj(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@Override
	public long getId() {
		return id;
	}
	@Override
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AbstractPersistentObj [id=" + id + ", name=" + name + "]";
	}
}
