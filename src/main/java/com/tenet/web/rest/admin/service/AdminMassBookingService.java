package com.tenet.web.rest.admin.service;

import org.springframework.data.domain.Pageable;

import com.tenet.web.rest.admin.dto.MarkAttendanceDTO;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.dto.response.BaseResponsePage;
import com.tenet.web.rest.common.specification.MassBookingSerachSpec;
import com.tenet.web.rest.profile.dto.MassBookingResponse;

public interface AdminMassBookingService {

	public BaseResponsePage<MassBookingResponse> searchMassBooking(MassBookingSerachSpec spec, Pageable pageable);

	public BaseResponse<String> markAttendance(MarkAttendanceDTO request);

}
