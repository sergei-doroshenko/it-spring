package org.training.issuetracker.domain;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.training.issuetracker.validation.CheckUser;

@Entity
@Table(name="USERS")
public class User {//extends AbstractPersistentObj 
	
	@Id
    @Column(name="ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="FIRST_NAME")
//	@Size(min=3,max=20,message="First name must be between 3 and 20 characters long.")
//	@Pattern(regexp="^[a-zA-Z0-9]+$", message="First name must be alphanumeric with nospaces")
	private String firstName;
	
	@Column(name="LAST_NAME")
//	@Size(min=3,max=20,message="Last name must be between 3 and 20 characters long.")
//	@Pattern(regexp="^[a-zA-Z0-9]+$", message="Last name must be alphanumeric with nospaces")
	private String lastName;
	
	@Column(name="EMAIL")
//	@Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message="Invalid email address.")
	private String email;
	
	@OneToOne()
	@JoinColumn(name="ROLE_ID")
	private Role role;
	
	@Column(name="PASSWORD")
//	@Size(min=6,max=20, message="The password must be at least 6 characters long.")
	private String password;

	public User() { }

	public User(long id, String firstName, String lastName,
			String email, Role role, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.password = password;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		JsonObject issueJson = Json.createObjectBuilder()
				.add("id", getId())
				.add("firstName", firstName)
				.add("lastName", lastName)
				.add("email", email)
				.add("password", password)
				.add("role", role.toString())
				.build();
		return issueJson.toString();
	}

	public JsonObject toJsonObj () {
		JsonObject issueJson = Json.createObjectBuilder()
							.add("id", getId())
							.add("role", getRole().getName())
							.add("name", getFirstName() + " " + getLastName())
						.build();
		return issueJson;
	}
}
