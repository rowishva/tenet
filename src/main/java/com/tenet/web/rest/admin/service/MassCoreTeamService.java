package com.tenet.web.rest.admin.service;

import org.springframework.data.domain.Pageable;

import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.dto.response.BaseResponsePage;
import com.tenet.web.rest.common.specification.CoreTeamSerachSpec;
import com.tenet.web.rest.profile.dto.MassCoreTeamDTO;

public interface MassCoreTeamService {

	public BaseResponse<MassCoreTeamDTO> createMassCoreTeam(Long massTimeId, MassCoreTeamDTO request);

	public BaseResponse<MassCoreTeamDTO> updateMassCoreTeam(Long massTimeId, Long massCoreTeamId,
			MassCoreTeamDTO request);

	public BaseResponse<MassCoreTeamDTO> deleteMassCoreTeam(Long massTimeId, Long massCoreTeamId);

	public BaseResponse<MassCoreTeamDTO> getMassCoreTeam(Long massTimeId, Long massCoreTeamId);

	public BaseResponse<MassCoreTeamDTO> getAllMassCoreTeam(Long massTimeId);

	public BaseResponsePage<MassCoreTeamDTO> searchMassCoreTeam(CoreTeamSerachSpec spec, Pageable pageable);

}
