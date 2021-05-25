package com.tenet.web.rest.admin.service;

import com.tenet.web.rest.admin.dto.AdminUserDTO;
import com.tenet.web.rest.common.dto.response.BaseResponse;

public interface AdminService {

	public BaseResponse<AdminUserDTO> createAdminUser(AdminUserDTO request);
	
	public BaseResponse<AdminUserDTO> updateAdminUser(AdminUserDTO request);
	
	public BaseResponse<AdminUserDTO> deleteAdminUser(long id);	
	
	public BaseResponse<AdminUserDTO> getAdminUser(long id);
	
	public BaseResponse<AdminUserDTO> getAllAdminUser();
	
}
