package com.tenet.web.rest.common.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.databind.JsonNode;
import com.tenet.web.rest.common.JsonNodeConverter;
import com.vladmihalcea.hibernate.type.json.JsonType;

@Entity
@Table(name = "mst_role")
@TypeDef(typeClass = JsonType.class, defaultForType = JsonNode.class)
@Where(clause = "is_deleted=0")
public class Role extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id = 0;

	@Column(name = "role_code", length = 10, nullable = false)
	private String roleCode;

	@Column(name = "description", length = 50, nullable = false)
	private String description;

	@Column(name = "privilege")
	@Convert(converter = JsonNodeConverter.class)
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
		builder.append("UserRole [id=");
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
