package com.tenet.web.rest.admin.service;

import org.springframework.data.domain.Pageable;

import com.tenet.web.rest.admin.dto.MassTimeDTO;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.dto.response.BaseResponsePage;
import com.tenet.web.rest.common.specification.MassTimeSerachSpec;

public interface MassTimeService {

	public BaseResponse<MassTimeDTO> createMassTime(MassTimeDTO request);

	public BaseResponse<MassTimeDTO> updateMassTime(Long id, MassTimeDTO request);

	public BaseResponse<MassTimeDTO> deleteMassTime(Long id);

	public BaseResponse<MassTimeDTO> getMassTime(Long id);

	public BaseResponsePage<MassTimeDTO> searchMassTime(MassTimeSerachSpec spec, Pageable pageable);
}
