package com.tenet.web.rest.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "mst_seating_prefix")
@Where(clause = "is_deleted=0")
public class SeatingPrefix extends BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "prefix", length = 10, nullable = false)
	private String prefix;

	@Column(name = "tag", length = 10, nullable = false)
	private String tag;

	@Column(name = "start_no")
	private int startNo;

	@Column(name = "end_no")
	private int endNo;

	@Column(name = "allocation_capacity")
	private int allocationCapacity;

	@Column(name = "carpark_allocation", length = 50, nullable = false)
	private String carParkAllocation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}

	public String getCarParkAllocation() {
		return carParkAllocation;
	}

	public void setCarParkAllocation(String carParkAllocation) {
		this.carParkAllocation = carParkAllocation;
	}

	public int getAllocationCapacity() {
		return allocationCapacity;
	}

	public void setAllocationCapacity(int allocationCapacity) {
		this.allocationCapacity = allocationCapacity;
	}
}
