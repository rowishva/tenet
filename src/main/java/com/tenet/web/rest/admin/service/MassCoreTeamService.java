package com.tenet.web.rest.admin.service;

import java.util.List;

import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.MassCoreTeamDTO;

public interface MassCoreTeamService {

	public BaseResponse<MassCoreTeamDTO> createMassCoreTeam(Long massTimeId, List<MassCoreTeamDTO> request);

	public BaseResponse<MassCoreTeamDTO> updateMassCoreTeam(Long massTimeId, List<MassCoreTeamDTO> request);

	public BaseResponse<MassCoreTeamDTO> getAllMassCoreTeam(Long massTimeId);

}
