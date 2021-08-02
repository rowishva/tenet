package com.tenet.web.rest.profile.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class SetNewPasswordDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
