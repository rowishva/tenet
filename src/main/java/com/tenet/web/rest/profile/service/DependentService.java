package com.tenet.web.rest.profile.service;

import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.DependentDTO;

public interface DependentService {

	public BaseResponse<DependentDTO> createDependent(Long profileId, DependentDTO request);

	public BaseResponse<DependentDTO> updateDependent(Long profileId, Long dependentId, DependentDTO request);
	
	public BaseResponse<DependentDTO> deleteDependent(Long profileId, Long dependentId);

	public BaseResponse<DependentDTO> getDependent(Long profileId, Long dependentId);
	
	public BaseResponse<DependentDTO> getAllDependents(Long profileId);

}
