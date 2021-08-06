package com.tenet.web.rest.profile.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class ProfileDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	@NotEmpty(message = "Full Name should not be empty")
	private String fullName;
	private String contactNumber;
	@NotEmpty(message = "Username should not be empty")
	@Email(message = "Username should be a valid Email")
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateOfBirth;
	private List<DependentDTO> dependents;
	private String roleCode;
	private int status;
	@NotEmpty(message = "Community Category should not be empty")
	private String communityCategory;
	@NotNull(message = "Special Needs should not be NULL")
	private int specialNeeds;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
