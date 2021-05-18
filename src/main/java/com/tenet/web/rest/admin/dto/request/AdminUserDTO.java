package com.tenet.web.rest.admin.dto.request;

import java.io.Serializable;

//import javax.validation.constraints.NotNull;

public class AdminUserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id = 0;
	// @NotNull
	private String username = "";
	private String firstName = "";
	private String lastName = "";
	private String roleCode = "";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminUserCreateRequest [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", roleCode=");
		builder.append(roleCode);
		builder.append("]");
		return builder.toString();
	}

}
