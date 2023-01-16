package com.tenet.web.rest.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "trn_mass_booking")
@Where(clause = "is_deleted=0")
@SequenceGenerator(name = "mass_booking_sequence_generator", sequenceName = "trn_mass_booking_sequence", initialValue = 1000, allocationSize = 1)
public class MassBooking extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mass_booking_sequence_generator")
	private Long id;

	@Column(name = "mass_time_id")
	private Long massTimeId;

	@Column(name = "prefix", length = 10, nullable = false)
	private String prefix;

	@Column(name = "tag", length = 10, nullable = false)
	private String tag;

	@Column(name = "seating_no")
	private int seatingNo;

	@Column(name = "carpark_allocation", length = 50, nullable = false)
	private String carParkAllocation;

	@Column(name = "mass_booking_no", length = 20)
	private String massBookingNo;

	@Column(name = "attendance")
	private Boolean attendance;

	@Column(name = "booked")
	private Boolean booked;

	@Column(name = "full_name", length = 50)
	private String fullName;

	@Column(name = "contact_number", length = 16)
	private String contactNumber;

	@Column(name = "profile_id")
	private Long profileId;

	@Column(name = "dependent_id")
	private Long dependentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMassTimeId() {
		return massTimeId;
	}

	public void setMassTimeId(Long massTimeId) {
		this.massTimeId = massTimeId;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
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

	public String getCarParkAllocation() {
		return carParkAllocation;
	}

	public void setCarParkAllocation(String carParkAllocation) {
		this.carParkAllocation = carParkAllocation;
	}

	public String getMassBookingNo() {
		return massBookingNo;
	}

	public void setMassBookingNo(String massBookingNo) {
		this.massBookingNo = massBookingNo;
	}

	public Boolean getAttendance() {
		return attendance;
	}

	public void setAttendance(Boolean attendance) {
		this.attendance = attendance;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getDependentId() {
		return dependentId;
	}

	public void setDependentId(Long dependentId) {
		this.dependentId = dependentId;
	}

	public Boolean getBooked() {
		return booked;
	}

	public void setBooked(Boolean booked) {
		this.booked = booked;
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
