package com.tenet.web.rest.admin.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.admin.dto.MarkAttendanceDTO;
import com.tenet.web.rest.admin.service.AdminMassBookingService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.dto.response.BaseResponsePage;
import com.tenet.web.rest.common.specification.MassBookingSerachSpec;
import com.tenet.web.rest.profile.dto.MassBookingResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Admin:Mass Booking")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_MASS_BOOKING)
public class AdminMassBookingController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private AdminMassBookingService service;

	@ApiOperation(value = "Search Mass Booking", response = BaseResponsePage.class)
	@GetMapping(value = ServiceEndpoints.SEARCH)
	public BaseResponsePage<MassBookingResponse> searchMassBooking(MassBookingSerachSpec spec, Pageable pageable) {
		LOGGER.debug("Calling AdminMassBookingController.searchMassBooking()");
		return service.searchMassBooking(spec, pageable);
	}

	@ApiOperation(value = "Mark Attendance", response = BaseResponsePage.class)
	@PutMapping(value = ServiceEndpoints.MARKATTENDANCE)
	public BaseResponse<String> markAttendance(@RequestBody MarkAttendanceDTO request) {
		LOGGER.debug("Calling AdminMassBookingController.markAttendance()");
		return service.markAttendance(request);
	}
}
