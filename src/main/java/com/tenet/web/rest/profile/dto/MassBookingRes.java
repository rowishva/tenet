package com.tenet.web.rest.profile.dto;

import java.io.Serializable;

public class MassBookingRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String fullName;
	private String contactNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
