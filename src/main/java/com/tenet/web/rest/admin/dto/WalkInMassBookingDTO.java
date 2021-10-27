package com.tenet.web.rest.admin.dto;

import java.io.Serializable;

public class WalkInMassBookingDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String fullName;
	private String contactNumber;
	private String prefix;
	private int seatingNo;

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

	public int getSeatingNo() {
		return seatingNo;
	}

	public void setSeatingNo(int seatingNo) {
		this.seatingNo = seatingNo;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
