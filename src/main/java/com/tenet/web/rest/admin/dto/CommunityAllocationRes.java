package com.tenet.web.rest.admin.dto;

import java.io.Serializable;

public class CommunityAllocationRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String code;
	private String description;
	private int totalAllocation;
	private int availableAllocation;

	public int getTotalAllocation() {
		return totalAllocation;
	}

	public void setTotalAllocation(int totalAllocation) {
		this.totalAllocation = totalAllocation;
	}

	public int getAvailableAllocation() {
		return availableAllocation;
	}

	public void setAvailableAllocation(int availableAllocation) {
		this.availableAllocation = availableAllocation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
