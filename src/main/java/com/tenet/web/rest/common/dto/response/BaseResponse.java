package com.tenet.web.rest.common.dto.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int status;

	private String message;

	private T response;

	private List<T> responseList;

	public BaseResponse(int status, String message, T response) {
		this.status = status;
		this.message = message;
		this.response = response;
	}

	public BaseResponse(int status, String message, List<T> responseList) {
		this.status = status;
		this.message = message;
		this.responseList = responseList;
	}

	public BaseResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public BaseResponse() {
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public List<T> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<T> responseList) {
		this.responseList = responseList;
	}

}
