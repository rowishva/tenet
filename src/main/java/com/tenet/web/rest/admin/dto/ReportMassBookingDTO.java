package com.tenet.web.rest.admin.dto;

import java.io.Serializable;

import com.tenet.web.rest.common.ApplicationConstants;

public class ReportMassBookingDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String massBookingNo = ApplicationConstants.EMPTY_STRING;
	private String fullName = ApplicationConstants.EMPTY_STRING;
	private String prefixSeatNo = ApplicationConstants.EMPTY_STRING;
	private String category = ApplicationConstants.EMPTY_STRING;
	private String carParkAllocation = ApplicationConstants.EMPTY_STRING;
	private String prefix = ApplicationConstants.EMPTY_STRING;
	private String tag = ApplicationConstants.EMPTY_STRING;
	private int seatingNo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMassBookingNo() {
		return massBookingNo;
	}

	public void setMassBookingNo(String massBookingNo) {
		if (massBookingNo != null)
			this.massBookingNo = massBookingNo;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		if (fullName != null)
			this.fullName = fullName;
	}

	public String getPrefixSeatNo() {
		prefixSeatNo = prefix + "-" + seatingNo;
		return prefixSeatNo;
	}

	public void setPrefixSeatNo(String prefixSeatNo) {
		if (prefixSeatNo != null)
			this.prefixSeatNo = prefixSeatNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		if (category != null)
			this.category = category;
	}

	public String getCarParkAllocation() {
		return carParkAllocation;
	}

	public void setCarParkAllocation(String carParkAllocation) {
		if (carParkAllocation != null)
			this.carParkAllocation = carParkAllocation;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		if (prefix != null)
			this.prefix = prefix;
	}

	public int getSeatingNo() {
		return seatingNo;
	}

	public void setSeatingNo(int seatingNo) {
		this.seatingNo = seatingNo;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getTagDescription() {
		return ApplicationConstants.TAG_DESCRIPTION.get(tag);
	}

}
