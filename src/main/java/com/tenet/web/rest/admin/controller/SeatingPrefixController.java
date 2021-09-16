package com.tenet.web.rest.admin.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.admin.dto.SeatingPrefixDTO;
import com.tenet.web.rest.admin.dto.SeatingPrefixRes;
import com.tenet.web.rest.admin.service.SeatingPrefixService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Admin:Seating Prefix")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_SEATING_PREFIX)
public class SeatingPrefixController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private SeatingPrefixService service;

	@ApiOperation(value = "Update Seating Prefix", response = BaseResponse.class)
	@PutMapping(value = ServiceEndpoints.ID)
	public BaseResponse<SeatingPrefixRes> updateSeatingPrefix(@PathVariable("id") Long id,
			@RequestBody SeatingPrefixDTO request) {
		LOGGER.debug("Calling SeatingPrefixController.updateSeatingPrefix()");
		return service.updateSeatingPrefix(id, request);
	}

	@ApiOperation(value = "Get All Seating Prefix", response = BaseResponse.class)
	@GetMapping
	public BaseResponse<SeatingPrefixRes> getAllSeatingPrefix() {
		LOGGER.debug("Calling SeatingPrefixController.getAllSeatingPrefix()");
		return service.getAllSeatingPrefix();
	}
}
