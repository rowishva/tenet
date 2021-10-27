package com.tenet.web.rest.admin.service;

import org.springframework.data.domain.Pageable;

import com.tenet.web.rest.admin.dto.MassCoreTeamDTO;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.dto.response.BaseResponsePage;
import com.tenet.web.rest.common.specification.CoreTeamSerachSpec;

public interface MassCoreTeamService {

	public BaseResponse<MassCoreTeamDTO> createMassCoreTeam(Long massTimeId, MassCoreTeamDTO request);

	public BaseResponse<MassCoreTeamDTO> updateMassCoreTeam(Long massTimeId, Long massCoreTeamId,
			MassCoreTeamDTO request);

	public BaseResponse<MassCoreTeamDTO> deleteMassCoreTeam(Long massTimeId, Long massCoreTeamId);

	public BaseResponse<MassCoreTeamDTO> getMassCoreTeam(Long massTimeId, Long massCoreTeamId);

	public BaseResponse<MassCoreTeamDTO> getAllMassCoreTeam(Long massTimeId);

	public BaseResponsePage<MassCoreTeamDTO> searchMassCoreTeam(CoreTeamSerachSpec spec, Pageable pageable);

}
