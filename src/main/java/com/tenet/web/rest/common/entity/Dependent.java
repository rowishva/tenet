package com.tenet.web.rest.common.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.tenet.web.rest.common.enums.SpecialNeeds;

@Entity
@Table(name = "trn_dependent")
@Where(clause = "is_deleted=0")
@SequenceGenerator(name = "dependent_sequence_generator", sequenceName = "trn_dependent_sequence", initialValue = 10000, allocationSize = 1)
public class Dependent extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dependent_sequence_generator")
	private Long id;

	@Column(name = "full_name", length = 50)
	private String fullName;

	@Column(name = "date_of_birth")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateOfBirth;

	@Column(name = "relationship", length = 50)
	private String relationship;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "profile_id")
	private Profile profile;

	@Column(name = "communityCategory", length = 50)
	private String communityCategory;

	@Enumerated(EnumType.STRING)
	@Column(name = "specialNeeds", length = 20)
	private SpecialNeeds specialNeeds;

	@Column(name = "contact_number", length = 16)
	private String contactNumber;

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getCommunityCategory() {
		return communityCategory;
	}

	public void setCommunityCategory(String communityCategory) {
		this.communityCategory = communityCategory;
	}

	public SpecialNeeds getSpecialNeeds() {
		return specialNeeds;
	}

	public void setSpecialNeeds(SpecialNeeds specialNeeds) {
		this.specialNeeds = specialNeeds;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
