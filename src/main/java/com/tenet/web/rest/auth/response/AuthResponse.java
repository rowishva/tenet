package com.tenet.web.rest.auth.response;

import java.io.Serializable;

import com.tenet.web.rest.profile.dto.RoleDTO;

public class AuthResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String token;
	private final String fullName;
	private final RoleDTO role;

	public AuthResponse(String token, String fullName, RoleDTO role) {
		super();
		this.token = token;
		this.fullName = fullName;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public String getFullName() {
		return fullName;
	}

	public RoleDTO getRole() {
		return role;
	}

}
