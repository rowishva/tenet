package com.tenet.web.rest.profile.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.MassBookingDTO;
import com.tenet.web.rest.profile.dto.MassBookingNoDTO;
import com.tenet.web.rest.profile.dto.MassBookingResponse;
import com.tenet.web.rest.profile.service.MassBookingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Mass Booking")
@RestController
@RequestMapping(ServiceEndpoints.MASS_BOOKING)
public class MassBookingController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private MassBookingService service;

	@ApiOperation(value = "Create new Mass Booking", response = BaseResponse.class)
	@PostMapping(value = ServiceEndpoints.MASSTIME_ID)
	public BaseResponse<MassBookingNoDTO> createMassBooking(@RequestBody MassBookingDTO request,
			@PathVariable("massTimeId") long massTimeId) {
		LOGGER.debug("Calling MassBookingController.createMassBooking()");
		return service.createMassBooking(request, massTimeId);
	}

	@ApiOperation(value = "Delete Mass Booking", response = BaseResponse.class)
	@DeleteMapping(value = ServiceEndpoints.MASSBOOKING_ID)
	public BaseResponse<MassBookingNoDTO> deleteMassBooking(@PathVariable("massBookingId") long massBookingId) {
		LOGGER.debug("Calling MassBookingController.deleteMassBooking()");
		return service.deleteMassBooking(massBookingId);
	}

	@ApiOperation(value = "Get All Mass Booking", response = BaseResponse.class)
	@GetMapping()
	public BaseResponse<MassBookingResponse> getAllMassBooking() {
		LOGGER.debug("Calling MassBookingController.getAllMassBooking()");
		return service.getAllMassBooking();
	}
}
