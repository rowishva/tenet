package com.tenet.web.rest.common.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import com.tenet.web.rest.common.enums.ProfileStatus;
import com.tenet.web.rest.common.enums.SpecialNeeds;

@Entity
@Table(name = "trn_profile", uniqueConstraints = @UniqueConstraint(columnNames = { "username" }))
@Where(clause = "is_deleted=0")
@SequenceGenerator(name = "profile_sequence_generator", sequenceName = "profile_sequence", initialValue = 10000, allocationSize = 1)
public class Profile extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_sequence_generator")
	private Long id;

	@Column(name = "full_name", length = 50)
	private String fullName;

	@Column(name = "contact_number", length = 16)
	private String contactNumber;

	@Column(name = "username", length = 50)
	private String username;

	@Column(name = "password", length = 200)
	private String password;

	@Column(name = "date_of_birth")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateOfBirth;

	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Dependent> dependents;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 20)
	private ProfileStatus status;

	@Column(name = "communityCategory", length = 50)
	private String communityCategory;

	@Enumerated(EnumType.STRING)
	@Column(name = "specialNeeds", length = 20)
	private SpecialNeeds specialNeeds;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Dependent> getDependents() {
		if (dependents == null) {
			dependents = new ArrayList<Dependent>();
		}
		return dependents;
	}

	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}

	public ProfileStatus getStatus() {
		return status;
	}

	public void setStatus(ProfileStatus status) {
		this.status = status;
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

}
