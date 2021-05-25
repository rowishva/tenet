package com.tenet.web.rest.admin.dto;

import java.io.Serializable;

import javax.persistence.Convert;

import com.fasterxml.jackson.databind.JsonNode;
import com.tenet.web.rest.common.JsonNodeConverter;

public class AdminUserRoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id = 0;
	private String roleCode = "";
	private String description = "";
	//@Convert(converter = JsonNodeConverter.class)
	private JsonNode privilege;
	
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

	public JsonNode getPrivilege() {
		return privilege;
	}

	public void setPrivilege(JsonNode privilege) {
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
