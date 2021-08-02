package com.tenet.web.rest.profile.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class ProfileUpdateDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fullName;
	private String contactNumber;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateOfBirth;
	private List<DependentDTO> dependents;
	private String communityCategory;
	private int specialNeeds;

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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<DependentDTO> getDependents() {
		return dependents;
	}

	public void setDependents(List<DependentDTO> dependents) {
		this.dependents = dependents;
	}

	public String getCommunityCategory() {
		return communityCategory;
	}

	public void setCommunityCategory(String communityCategory) {
		this.communityCategory = communityCategory;
	}

	public int getSpecialNeeds() {
		return specialNeeds;
	}

	public void setSpecialNeeds(int specialNeeds) {
		this.specialNeeds = specialNeeds;
	}

}
