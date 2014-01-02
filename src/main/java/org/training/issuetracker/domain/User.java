package org.training.issuetracker.domain;

public class User extends AbstractPersistentObj {
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	private String password;
	
	public User() { }
	
	public User(long id, String name, String firstName, String lastName, String email, Role role,
			String password) {
		super(id, name);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
