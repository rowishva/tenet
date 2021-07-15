package com.tenet.web.rest.profile.service;

import java.util.List;

import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.DependentDTO;

public interface DependentService {

	public BaseResponse<DependentDTO> createDependents(Long profileId, List<DependentDTO> request);

	public BaseResponse<DependentDTO> updateDependents(Long profileId, List<DependentDTO> request);

	public BaseResponse<DependentDTO> getDependents(Long profileId);

}
