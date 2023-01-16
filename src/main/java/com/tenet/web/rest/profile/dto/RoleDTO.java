package com.tenet.web.rest.profile.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	@NotEmpty(message = "Role Code should not be empty")
	private String roleCode;
	@NotEmpty(message = "Description should not be empty")
	private String description;
	private List<String> privilege;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminUserRoleDTO [id=");
		builder.append(id);
		builder.append(", roleCode=");
		builder.append(roleCode);
		builder.append(", description=");
		builder.append(description);
		builder.append(", privilege=");
		builder.append(privilege);
		builder.append("]");
		return builder.toString();
	}
}
