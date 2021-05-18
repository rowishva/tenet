package com.tenet.web.rest.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_user_role")
public class AdminUserRole extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = 0;

	@Column(name = "role_code")
	private String roleCode = "";

	@Column(name = "description")
	private String description = "";

	@Column(name = "responseentity")
	private String responseentity = "";

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

	public String getResponseentity() {
		return responseentity;
	}

	public void setResponseentity(String responseentity) {
		this.responseentity = responseentity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRole [id=");
		builder.append(id);
		builder.append(", roleCode=");
		builder.append(roleCode);
		builder.append(", description=");
		builder.append(description);
		builder.append(", responseentity=");
		builder.append(responseentity);
		builder.append("]");
		return builder.toString();
	}

}
