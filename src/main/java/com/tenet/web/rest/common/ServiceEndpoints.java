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
	public static final String ADMIN_MASS_TIME = "admin/masstime";
	public static final String ADMIN_MASS_CORE_TEAM = "admin/masscoreteam/{massTimeId}";
	public static final String ADMIN_COMMUNITY_ALLOCATION = "admin/communityallocation";
	public static final String ADMIN_PROFILE = "admin/profile";
	public static final String CHANGEROLE = "/changerole/{profileid}/{torolecode}";
	public static final String ADMIN_GLOBAL_PARAMETER = "admin/globalparameter";

	public static final String ID = "/{id}";
	public static final String DEPENDENT_ID = "/{dependentId}";
	public static final String MASSCORETEAM_ID = "/{massCoreTeamId}";
	public static final String PAGE = "/{pageNo}/{pageSize}";

}
