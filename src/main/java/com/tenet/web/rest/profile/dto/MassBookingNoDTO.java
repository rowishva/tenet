package com.tenet.web.rest.profile.dto;

import java.io.Serializable;

public class MassBookingNoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String massBookingNo;

	public MassBookingNoDTO(String massBookingNo) {
		super();
		this.massBookingNo = massBookingNo;
	}

	public String getMassBookingNo() {
		return massBookingNo;
	}

	public void setMassBookingNo(String massBookingNo) {
		this.massBookingNo = massBookingNo;
	}

}
