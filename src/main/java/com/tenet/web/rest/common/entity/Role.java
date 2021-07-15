package com.tenet.web.rest.common.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.databind.JsonNode;
import com.tenet.web.rest.common.config.StringListConverter;
import com.vladmihalcea.hibernate.type.json.JsonType;

@Entity
@Table(name = "mst_role")
@TypeDef(typeClass = JsonType.class, defaultForType = JsonNode.class)
@Where(clause = "is_deleted=0")
@SequenceGenerator(name = "role_sequence_generator", sequenceName = "role_sequence", initialValue = 1, allocationSize = 1)
public class Role extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence_generator")
	private Long id;

	@Column(name = "role_code", length = 10, nullable = false)
	private String roleCode;

	@Column(name = "description", length = 50, nullable = false)
	private String description;

	@Column(name = "privilege", length = 1000)
	@Convert(converter = StringListConverter.class)
	private List<String> privilege;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
