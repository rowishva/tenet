package com.tenet.web.rest.admin.service;

import com.tenet.web.rest.admin.dto.AllocationDTO;
import com.tenet.web.rest.admin.dto.AllocationRes;
import com.tenet.web.rest.common.dto.response.BaseResponse;

public interface CommunityAllocationService {

	public BaseResponse<AllocationRes> updateCommunityAllocation(Long id, AllocationDTO request);

	public BaseResponse<AllocationRes> getAllCommunityAllocation();
}
