package com.tenet.web.rest.common.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum SpecialNeeds {

	NONE(0, "None"), ELDERLY(1, "Elderly"), OKU(2, "OKU"), SPECIALNEED_HELPER(3, "Special need + Helper");

	private int code;

	private String name;

	private SpecialNeeds(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private static final Map<Integer, SpecialNeeds> LOOKUP = new HashMap<Integer, SpecialNeeds>();

	static {
		for (SpecialNeeds specialNeeds : EnumSet.allOf(SpecialNeeds.class)) {
			LOOKUP.put(specialNeeds.getCode(), specialNeeds);
		}
	}

	public static SpecialNeeds fromCode(int code) {
		return LOOKUP.get(code);
	}

}
