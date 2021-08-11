package com.tenet.web.rest.admin.service;

import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.ProfileDTO;

public interface AdminProfileService {

	public BaseResponse<ProfileDTO> changeRole(Long id, String toRoleCode);
}
