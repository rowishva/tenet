package com.tenet.web.rest.admin.service;

import com.tenet.web.rest.admin.dto.CommunityAllocationDTO;
import com.tenet.web.rest.admin.dto.CommunityAllocationRes;
import com.tenet.web.rest.common.dto.response.BaseResponse;

public interface CommunityAllocationService {

	public BaseResponse<CommunityAllocationRes> updateCommunityAllocation(Long id, CommunityAllocationDTO request);

	public BaseResponse<CommunityAllocationRes> getAllCommunityAllocation();
}
