package com.tenet.web.rest.common.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ProfileStatus {

	ACTIVE(0, "Active"), CLOSED(1, "Closed"), DISABLED(2, "Disabled"), BLACKLIST(3, "BlackList"),OTPVERIFICATION(4, "OTPVerification");

	private int code;

	private String name;

	private ProfileStatus(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private static final Map<Integer, ProfileStatus> LOOKUP = new HashMap<Integer, ProfileStatus>();

	static {
		for (ProfileStatus profileStatus : EnumSet.allOf(ProfileStatus.class)) {
			LOOKUP.put(profileStatus.getCode(), profileStatus);
		}
	}

	public static ProfileStatus fromCode(int code) {
		return LOOKUP.get(code);
	}

}
