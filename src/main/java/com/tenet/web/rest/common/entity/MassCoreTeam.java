package com.tenet.web.rest.common.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "trn_mass_core_team")
@Where(clause = "is_deleted=0")
@SequenceGenerator(name = "mass_core_team_sequence_generator", sequenceName = "mass_core_team_sequence", initialValue = 1000, allocationSize = 1)
public class MassCoreTeam extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mass_core_team_sequence_generator")
	private Long id;

	@Column(name = "full_name", length = 50)
	private String fullName;

	@Column(name = "contact_number", length = 16)
	private String contactNumber;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "mass_time_id", referencedColumnName = "id")
	private MassTime massTime;

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

	public MassTime getMassTime() {
		return massTime;
	}

	public void setMassTime(MassTime massTime) {
		this.massTime = massTime;
	}

}
