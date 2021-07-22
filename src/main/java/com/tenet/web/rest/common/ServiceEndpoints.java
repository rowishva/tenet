package com.tenet.web.rest.common;

public final class ServiceEndpoints {

	public static final String ADMIN_NAMESAPCE = "Admin";
	public static final String PROFILE = "profile";
	public static final String PROFILE_ROLE = "profile/role";
	public static final String PROFILE_LOGIN = "profile/login";
	public static final String REGISTER = "/register";
	public static final String PROFILE_DEPENDENT = "profile/{profileId}/dependent";
	public static final String PROFILE_SEND_NEW_OTP = "send/new/otp/{username}";
	public static final String PROFILE_FORGOT_PASSWORD = "forgot/password/{username}";
	public static final String PROFILE_SET_NEW_PASSWORD = "set/new/password/{username}";
	public static final String PROFILE_OTP_VERIFICATION = "otp/verification/{username}/{otp}";

	public static final String ID = "/{id}";

}
