package com.tenet.web.rest.admin.service;

import com.tenet.web.rest.admin.dto.request.AdminUserDTO;
import com.tenet.web.rest.common.dto.response.BaseResponse;

public interface AdminService {

	public BaseResponse createAdminUser(AdminUserDTO request);
}
