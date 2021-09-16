package com.tenet.web.rest.admin.dto;

import java.io.Serializable;

public class AllocationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

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

}
