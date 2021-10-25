package com.tenet.web.rest.profile.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MassBookingDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean profileBooking;
	private List<Long> dependents;

	public boolean isProfileBooking() {
		return profileBooking;
	}

	public void setProfileBooking(boolean profileBooking) {
		this.profileBooking = profileBooking;
	}

	public List<Long> getDependents() {
		if (dependents == null) {
			dependents = new ArrayList<Long>();
		}
		return dependents;
	}

	public void setDependents(List<Long> dependents) {
		this.dependents = dependents;
	}
}
