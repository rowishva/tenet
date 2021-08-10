package com.tenet.web.rest.admin.service;

import com.tenet.web.rest.admin.dto.MassTimeDTO;
import com.tenet.web.rest.common.dto.response.BaseResponse;

public interface MassTimeService {

	public BaseResponse<MassTimeDTO> createMassTime(MassTimeDTO request);

	public BaseResponse<MassTimeDTO> updateMassTime(Long id, MassTimeDTO request);

	public BaseResponse<MassTimeDTO> deleteMassTime(Long id);

	public BaseResponse<MassTimeDTO> getMassTime(Long id);

	public BaseResponse<MassTimeDTO> getAllMassTime(Integer pageNo, Integer pageSize, String sortBy, String direction);
}
