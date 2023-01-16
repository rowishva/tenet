package com.tenet.web.rest.admin.service;

import org.springframework.data.domain.Pageable;

import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.dto.response.BaseResponsePage;
import com.tenet.web.rest.common.specification.ProfileSerachSpec;
import com.tenet.web.rest.profile.dto.ProfileDTO;

public interface AdminProfileService {

	public BaseResponse<ProfileDTO> changeRole(Long id, String toRoleCode);

	public BaseResponsePage<ProfileDTO> searchProfile(ProfileSerachSpec spec, Pageable pageable);
}
