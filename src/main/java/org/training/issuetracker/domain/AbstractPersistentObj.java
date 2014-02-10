package org.training.issuetracker.domain;

import javax.json.Json;
import javax.json.JsonObject;

/**Abstract class for all persist objects.
 * @author Sergei_Doroshenko
 *
 */
public abstract class AbstractPersistentObj implements PersistentObj {
	private long id;
	private String name;

	/**Default constructor.
	 *
	 */
	public AbstractPersistentObj() { }

	/**Constructor with parameters.
	 * @param id - persist object id
	 * @param name - persist object name
	 */
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
		JsonObject issueJson = Json.createObjectBuilder()
				.add("id", getId())
				.add("name", getName())
				.build();
		return issueJson.toString();
	}
}
