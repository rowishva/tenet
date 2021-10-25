package com.tenet.web.rest.profile.dto;

import java.io.Serializable;

public class MassBookingCategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tag;
	private int totalCapacity;
	private int availableCapacity;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public int getAvailableCapacity() {
		return availableCapacity;
	}

	public void setAvailableCapacity(int availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

}
