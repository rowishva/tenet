package com.tenet.web.rest.admin.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.admin.dto.WalkInMassBookingDTO;
import com.tenet.web.rest.admin.service.WalkInMassBookingService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Admin:Walk In Mass Booing")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_WALK_IN_MASS_BOOKING)
public class WalkInMassBooingController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private WalkInMassBookingService service;

	@ApiOperation(value = "Create new Walk In Mass Booking", response = BaseResponse.class)
	@PostMapping(value = ServiceEndpoints.MASSTIME_ID)
	public BaseResponse<WalkInMassBookingDTO> createWalkInMassBooking(@PathVariable("massTimeId") Long massTimeId,
			@RequestBody WalkInMassBookingDTO request) {
		LOGGER.debug("Calling WalkInMassBooingController.createWalkInMassBooking()");
		return service.createWalkInMassBooking(massTimeId, request);
	}

	@ApiOperation(value = "Update Walk In Mass Booking", response = BaseResponse.class)
	@PutMapping(value = ServiceEndpoints.MASSBOOKING_ID)
	public BaseResponse<WalkInMassBookingDTO> updateWalkInMassBooking(@PathVariable("massBookingId") Long massBookingId,
			@RequestBody WalkInMassBookingDTO request) {
		LOGGER.debug("Calling WalkInMassBooingController.updateWalkInMassBooking()");
		return service.updateWalkInMassBooking(massBookingId, request);
	}

	@ApiOperation(value = "Delete Walk In Mass Booking", response = BaseResponse.class)
	@DeleteMapping(value = ServiceEndpoints.MASSBOOKING_ID)
	public BaseResponse<WalkInMassBookingDTO> deleteWalkInMassBooking(
			@PathVariable("massBookingId") Long massBookingId) {
		LOGGER.debug("Calling WalkInMassBooingController.deleteWalkInMassBooking()");
		return service.deleteWalkInMassBooking(massBookingId);
	}

	@ApiOperation(value = "Get All Walk In Mass Booking", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.MASSTIME_ID)
	public BaseResponse<WalkInMassBookingDTO> getAllWalkInMassBooking(@PathVariable("massTimeId") Long massTimeId) {
		LOGGER.debug("Calling WalkInMassBooingController.getAllWalkInMassBooking()");
		return service.getAllWalkInMassBooking(massTimeId);
	}

}
