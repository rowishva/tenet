package com.tenet.web.rest.common.service;

import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.RoleDTO;

public interface RoleService {

	public BaseResponse<RoleDTO> createRole(RoleDTO request);

	public BaseResponse<RoleDTO> updateRole(long id, RoleDTO request);

	public BaseResponse<RoleDTO> deleteRole(long id);

	public BaseResponse<RoleDTO> getRole(long id);

	public BaseResponse<RoleDTO> getAllRole();

}
