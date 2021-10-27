package com.tenet.web.rest.admin.service;

import com.tenet.web.rest.admin.dto.WalkInMassBookingDTO;
import com.tenet.web.rest.common.dto.response.BaseResponse;

public interface WalkInMassBookingService {

	public BaseResponse<WalkInMassBookingDTO> createWalkInMassBooking(Long massTimeId, WalkInMassBookingDTO request);

	public BaseResponse<WalkInMassBookingDTO> updateWalkInMassBooking(Long massBookingId, WalkInMassBookingDTO request);

	public BaseResponse<WalkInMassBookingDTO> deleteWalkInMassBooking(Long massBookingId);

	public BaseResponse<WalkInMassBookingDTO> getAllWalkInMassBooking(Long massTimeId);

}
