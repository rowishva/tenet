package com.tenet.web.rest.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.databind.JsonNode;
import com.vladmihalcea.hibernate.type.json.JsonType;

@Entity
@Table(name = "mst_seating_category")
@TypeDef(typeClass = JsonType.class, defaultForType = JsonNode.class)
@Where(clause = "is_deleted=0")
public class SeatingCategory extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "code", length = 10, nullable = false)
	private String code;

	@Column(name = "description", length = 50, nullable = false)
	private String description;

	@Column(name = "total_allocation")
	private int totalAllocation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getTotalAllocation() {
		return totalAllocation;
	}

	public void setTotalAllocation(int totalAllocation) {
		this.totalAllocation = totalAllocation;
	}
}
