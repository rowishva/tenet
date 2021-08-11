package com.tenet.web.rest.common;

public interface ApplicationConstants {

	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	public static final String UNSUCCESS = "UNSUCCESS";

	// The operation was successful and a response has been returned.
	public static int STATUS_CODE_OK = 200;
	// The operation was successful and the entity has been created and is returned
	// in the response-body (POST request).
	public static int STATUS_CODE_CREATED = 201;
	public static int STATUS_CODE_CONFLICT = 409;

	public static final String UNAUTHORIZED = "Unauthorized";
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer ";
	public static final long TOKEN_VALIDITY = 1 * 60 * 60;

	public static final String ERROR_MSG_PROFILE_NOT_FOUND = "Profile Not Found, Invalid Id :";
	public static final String ERROR_MSG_DEPENDENT_NOT_FOUND = "Dependent Not Found, Invalid Id :";
	public static final String ERROR_MSG_USERNAME_NOT_FOUND = "Username Not Found, Invalid username :";
	public static final String ERROR_MSG_INVALID_OTP = "Invalid OTP :";
	public static final String ERROR_MSG_MASSTIME_NOT_FOUND = "Mass Time Not Found, Invalid Id :";
	public static final String ERROR_MSG_MASSCORETEAM_NOT_FOUND = "Mass Core Team Not Found, Invalid Id :";
	public static final String ERROR_MSG_COMMUNITYALLOCATION_NOT_FOUND = "Community Allocation Not Found, Invalid Id :";
	public static final String ERROR_MSG_USERNAME_ALREADY_EXISTS = "Username Already Exists, Username :";
	public static final String ERROR_MSG_MALFORMED_JSON_REQUEST = "Malformed JSON request";
	public static final String ERROR_MSG_ROLE_NOT_FOUND = "Role Not Found, Invalid Id :";
	public static final String ERROR_MSG_INTERNAL_SERVER_ERROR = "Internal Server error Please contact administrator";
	public static final String ERROR_MSG_GLOBALPARAMETER_NOT_FOUND = "Global Parameter Not Found, Invalid Id :";

}
