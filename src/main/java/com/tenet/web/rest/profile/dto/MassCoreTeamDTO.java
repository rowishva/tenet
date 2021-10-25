package com.tenet.web.rest.profile.dto;

import java.io.Serializable;

public class MassCoreTeamDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String fullName;
	private String tag;
	private String contactNumber;
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

}
