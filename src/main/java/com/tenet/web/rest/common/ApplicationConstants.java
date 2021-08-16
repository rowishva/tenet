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
	public static final String ERROR_MSG_OTP_INVALID = "Invalid OTP :";
	public static final String ERROR_MSG_OTP_NULL = "Invalid OTP : OTP is null";
	public static final String ERROR_MSG_MASSTIME_NOT_FOUND = "Mass Time Not Found, Invalid Id :";
	public static final String ERROR_MSG_MASSCORETEAM_NOT_FOUND = "Mass Core Team Not Found, Invalid Id :";
	public static final String ERROR_MSG_COMMUNITYALLOCATION_NOT_FOUND = "Community Allocation Not Found, Invalid Id :";
	public static final String ERROR_MSG_USERNAME_ALREADY_EXISTS = "Username Already Exists, Username :";
	public static final String ERROR_MSG_MALFORMED_JSON_REQUEST = "Malformed JSON request";
	public static final String ERROR_MSG_ROLE_NOT_FOUND = "Role Not Found, Invalid Id :";
	public static final String ERROR_MSG_INTERNAL_SERVER_ERROR = "Internal Server error Please contact administrator";
	public static final String ERROR_MSG_GLOBALPARAMETER_NOT_FOUND = "Global Parameter Not Found, Invalid Id :";
	public static final String ERROR_MSG_DEPENDENT_FOUND = "Dependent Already Exists, Full Name & Date of Birth : ";
	public static final String ERROR_MSG_MASSTIME_FOUND = "Mass Time Already Exists, Date & Time :";
	public static final String ERROR_MSG_TOKEN_NULL = "You are not authorized: Token is null";
	public static final String ERROR_MSG_TOKEN_INVALID = "You are not authorized: Token is Invalid";
	public static final String ERROR_MSG_TOKEN_EXPIRED = "You are not authorized: Token is Expired";
	public static final String ERROR_MSG_MISSING_MANDATORY_PARAMETERS = "Bad request missing mandatory parameters";

}
