package com.tenet.web.rest.admin.dto;

import java.io.Serializable;

public class SeatingPrefixRes implements Serializable {

	private static final long serialVersionUID = 1L;

	Long id;
	String prefix;
	String tag;
	int startNo;
	int endNo;
	int allocationCapacity;
	String carParkAllocation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

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
