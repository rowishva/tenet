package com.tenet.web.rest.admin.dto;

import java.io.Serializable;

public class GlobalParameterDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String code;
	private String description;
	private String value;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
