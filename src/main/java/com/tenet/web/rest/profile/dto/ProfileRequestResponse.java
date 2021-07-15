package com.tenet.web.rest.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequestResponse {

	private String id;
	private String fullName;
	private String nricPassport;
	private String gender;
	private String nationality;
	private String parishGroup;
	private String contactNumber;
	private String email;
	private String password;

}
