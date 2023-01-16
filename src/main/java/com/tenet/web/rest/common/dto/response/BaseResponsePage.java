package com.tenet.web.rest.common.dto.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponsePage<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int status;
	private String message;
	private List<T> responseList;
	private long totalElements;
	private long totalPages;

	public BaseResponsePage(int status, String message, List<T> responseList) {
		this.status = status;
		this.message = message;
		this.responseList = responseList;
	}

	public BaseResponsePage(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public BaseResponsePage(int status, String message, List<T> responseList, long totalElements, long totalPages) {
		this.status = status;
		this.message = message;
		this.responseList = responseList;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}

	public BaseResponsePage() {
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

	public List<T> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<T> responseList) {
		this.responseList = responseList;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

}
