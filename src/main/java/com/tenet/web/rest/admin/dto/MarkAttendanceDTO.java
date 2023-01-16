package com.tenet.web.rest.admin.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MarkAttendanceDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Long> attendanceList;

	public List<Long> getAttendanceList() {
		if (attendanceList == null) {
			attendanceList = new ArrayList<Long>();
		}
		return attendanceList;
	}

	public void setAttendanceList(List<Long> attendanceList) {
		this.attendanceList = attendanceList;
	}
}
