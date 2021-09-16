package com.tenet.web.rest.admin.service;

import com.tenet.web.rest.admin.dto.AllocationDTO;
import com.tenet.web.rest.admin.dto.AllocationRes;
import com.tenet.web.rest.common.dto.response.BaseResponse;

public interface SeatingCategoryService {

	public BaseResponse<AllocationRes> updateSeatingCategory(Long id, AllocationDTO request);

	public BaseResponse<AllocationRes> getAllSeatingCategory();
}
