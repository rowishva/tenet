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
	public static final String PROFILE_FORGOT_PASSWORD_OTP_VERIFICATION = "forgot/password/otp/verification/{username}/{otp}";
	public static final String ADMIN_MASS_TIME = "admin/masstime";
	public static final String ADMIN_MASS_CORE_TEAM = "admin/masscoreteam";
	public static final String ADMIN_COMMUNITY_ALLOCATION = "admin/communityallocation";
	public static final String ADMIN_SEATING_CATEGORY = "admin/seatingcategory";
	public static final String ADMIN_SEATING_PREFIX = "admin/seatingprefix";
	public static final String ADMIN_PROFILE = "admin/profile";
	public static final String CHANGEROLE = "/changerole/{profileid}/{torolecode}";
	public static final String ADMIN_GLOBAL_PARAMETER = "admin/globalparameter";

	public static final String ID = "/{id}";
	public static final String DEPENDENT_ID = "/{dependentId}";
	public static final String MASSCORETEAM_ID = "/{massTimeId}/{massCoreTeamId}";
	public static final String MASSTIME_ID = "/{massTimeId}";
	public static final String PAGE = "/{pageNo}/{pageSize}";
	public static final String SEARCH = "/search";
	public static final String MASS_BOOKING = "massbooking";
	public static final String MASS_BOOKING_CATEGORY = "massbookingcategory";
	public static final String ADMIN_MASS_BOOKING_CATEGORY = "admin/massbookingcategory";
	public static final String ADMIN_MASS_BOOKING = "admin/massbooking";
	public static final String MARKATTENDANCE = "/markattendance";
	public static final String MASSBOOKING_ID = "/{massBookingId}";

}
