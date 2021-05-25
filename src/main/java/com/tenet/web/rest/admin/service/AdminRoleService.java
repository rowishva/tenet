package com.tenet.web.rest.admin.service;

import com.tenet.web.rest.admin.dto.AdminUserRoleDTO;
import com.tenet.web.rest.common.dto.response.BaseResponse;

public interface AdminRoleService {

	public BaseResponse<AdminUserRoleDTO> createAdminUserRole(AdminUserRoleDTO request);
	
	public BaseResponse<AdminUserRoleDTO> updateAdminUserRole(AdminUserRoleDTO request);
	
	public BaseResponse<AdminUserRoleDTO> deleteAdminUserRole(long id);	
	
	public BaseResponse<AdminUserRoleDTO> getAdminUserRole(long id);
	
	public BaseResponse<AdminUserRoleDTO> getAllAdminUserRole();
	
}
