package com.tenet.web.rest.profile.dto;

import java.io.Serializable;

public class MassBookingRes implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String fullName;
	private String contactNumber;
	private String code;
	private String tag;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getSeatingNo() {
		return seatingNo;
	}

	public void setSeatingNo(int seatingNo) {
		this.seatingNo = seatingNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}