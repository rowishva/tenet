package com.tenet.web.rest.profile.service;

import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.MassBookingCategoryDTO;

public interface MassBookingCategoryService {

	public BaseResponse<MassBookingCategoryDTO> getMassBookingCategory(long massTimeId);

	public BaseResponse<MassBookingCategoryDTO> getAdminMassBookingCategory(long massTimeId);

}
