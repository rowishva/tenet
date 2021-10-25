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
@Table(name = "trn_mass_booking_category")
@Where(clause = "is_deleted=0")
@SequenceGenerator(name = "mass_booking_category_sequence_generator", sequenceName = "trn_mass_booking_category_sequence", initialValue = 1000, allocationSize = 1)
public class MassBookingCategory extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mass_booking_category_sequence_generator")
	private Long id;

	@Column(name = "tag", length = 10, nullable = false)
	private String tag;

	@Column(name = "total_capacity")
	private int totalCapacity;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "mass_time_id", referencedColumnName = "id")
	private MassTime massTime;

	@Column(name = "available_capacity")
	private int availableCapacity;

	@Column(name = "next_allocation_sequence", length = 5)
	private String nextAllocationSequence;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public MassTime getMassTime() {
		return massTime;
	}

	public void setMassTime(MassTime massTime) {
		this.massTime = massTime;
	}

	public int getTotalCapacity() {
		return totalCapacity;
	}

	public void setTotalCapacity(int totalCapacity) {
		this.totalCapacity = totalCapacity;
	}

	public int getAvailableCapacity() {
		return availableCapacity;
	}

	public void setAvailableCapacity(int availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

	public String getNextAllocationSequence() {
		return nextAllocationSequence;
	}

	public void setNextAllocationSequence(String nextAllocationSequence) {
		this.nextAllocationSequence = nextAllocationSequence;
	}

}
