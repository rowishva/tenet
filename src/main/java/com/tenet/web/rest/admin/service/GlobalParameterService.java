package com.tenet.web.rest.admin.service;

import com.tenet.web.rest.admin.dto.GlobalParameterDTO;
import com.tenet.web.rest.common.dto.response.BaseResponse;

public interface GlobalParameterService {

	public BaseResponse<GlobalParameterDTO> updateGlobalParameter(Long id, GlobalParameterDTO request);

	public BaseResponse<GlobalParameterDTO> getAllGlobalParameter();
}
