package com.tenet.web.rest.admin.dto;

import java.io.Serializable;

public class SeatingPrefixDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	int startNo;
	int endNo;
	int allocationCapacity;
	String carParkAllocation;

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public String getCarParkAllocation() {
		return carParkAllocation;
	}

	public void setCarParkAllocation(String carParkAllocation) {
		this.carParkAllocation = carParkAllocation;
	}

	public int getAllocationCapacity() {
		return allocationCapacity;
	}

	public void setAllocationCapacity(int allocationCapacity) {
		this.allocationCapacity = allocationCapacity;
	}
}
