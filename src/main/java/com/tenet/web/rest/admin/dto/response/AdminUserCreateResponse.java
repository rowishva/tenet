package com.tenet.web.rest.admin.dto.response;

import com.tenet.web.rest.common.dto.response.BaseResponse;

public class AdminUserCreateResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
