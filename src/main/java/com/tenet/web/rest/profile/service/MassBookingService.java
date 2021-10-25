package com.tenet.web.rest.profile.service;

import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.MassBookingDTO;
import com.tenet.web.rest.profile.dto.MassBookingNoDTO;
import com.tenet.web.rest.profile.dto.MassBookingResponse;

public interface MassBookingService {

	public BaseResponse<MassBookingNoDTO> createMassBooking(MassBookingDTO request, long massTimeId);

	public BaseResponse<MassBookingNoDTO> deleteMassBooking(long massBooingId);

	public BaseResponse<MassBookingResponse> getAllMassBooking();

}
