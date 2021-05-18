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
}
