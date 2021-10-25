package com.tenet.web.rest.profile.dto;

import java.io.Serializable;
import java.util.List;

import com.tenet.web.rest.admin.dto.MassTimeRes;

public class MassBookingResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private MassTimeRes massTime;
	private String massBookingNo;
	private List<MassBookingRes> massBookingList;

	public MassBookingResponse(MassTimeRes massTime, String massBookingNo, List<MassBookingRes> massBookingList) {
		super();
		this.massTime = massTime;
		this.massBookingNo = massBookingNo;
		this.massBookingList = massBookingList;
	}

	public MassTimeRes getMassTime() {
		return massTime;
	}

	public void setMassTime(MassTimeRes massTime) {
		this.massTime = massTime;
	}

	public String getMassBookingNo() {
		return massBookingNo;
	}

	public void setMassBookingNo(String massBookingNo) {
		this.massBookingNo = massBookingNo;
	}

	public List<MassBookingRes> getMassBookingList() {
		return massBookingList;
	}

	public void setMassBookingList(List<MassBookingRes> massBookingList) {
		this.massBookingList = massBookingList;
	}

}
