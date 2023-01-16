package com.tenet.web.rest.profile.dto;

import java.io.Serializable;

public class ForgotPasswordResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String resetToken;

	public ForgotPasswordResponse(String resetToken) {
		this.resetToken = resetToken;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

}
