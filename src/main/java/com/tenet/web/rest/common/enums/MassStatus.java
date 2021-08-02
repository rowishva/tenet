package com.tenet.web.rest.common.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum MassStatus {

	ACTIVE(0, "Active"), CANCELED(1, "Canceled"), ONHOLD(2, "On Hold"), FULLYBOOKED(3, "Fully Booked");

	private int code;

	private String name;

	private MassStatus(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private static final Map<Integer, MassStatus> LOOKUP = new HashMap<Integer, MassStatus>();

	static {
		for (MassStatus profileStatus : EnumSet.allOf(MassStatus.class)) {
			LOOKUP.put(profileStatus.getCode(), profileStatus);
		}
	}

	public static MassStatus fromCode(int code) {
		return LOOKUP.get(code);
	}

}
