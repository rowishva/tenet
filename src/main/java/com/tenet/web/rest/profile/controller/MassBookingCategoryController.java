package com.tenet.web.rest.profile.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.MassBookingCategoryDTO;
import com.tenet.web.rest.profile.service.MassBookingCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Mass Booking Category")
@RestController
@RequestMapping(ServiceEndpoints.MASS_BOOKING_CATEGORY)
public class MassBookingCategoryController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private MassBookingCategoryService service;

	@ApiOperation(value = "Get Mass Booking Category", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.MASSTIME_ID)
	public BaseResponse<MassBookingCategoryDTO> getMassBookingCategory(@PathVariable("massTimeId") Long massTimeId) {
		LOGGER.debug("Calling MassBookingController.getMassBookingCategory()");
		return service.getMassBookingCategory(massTimeId);
	}
}
