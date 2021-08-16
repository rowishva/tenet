package com.tenet.web.rest.admin.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.admin.dto.MassTimeDTO;
import com.tenet.web.rest.admin.service.MassTimeService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.dto.response.BaseResponsePage;
import com.tenet.web.rest.common.specification.MassTimeSerachSpec;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Admin:Mass Time")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_MASS_TIME)
public class MassTimeController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private MassTimeService service;

	@ApiOperation(value = "Create new Mass Time", response = BaseResponse.class)
	@PostMapping
	public BaseResponse<MassTimeDTO> createMassTime(@RequestBody MassTimeDTO request) {
		LOGGER.debug("Calling MassTimeController.createMassTime()");
		return service.createMassTime(request);
	}

	@ApiOperation(value = "Update Mass Time", response = BaseResponse.class)
	@PutMapping(value = ServiceEndpoints.ID)
	public BaseResponse<MassTimeDTO> updateMassTime(@PathVariable("id") long id, @RequestBody MassTimeDTO request) {
		LOGGER.debug("Calling MassTimeController.updateMassTime()");
		return service.updateMassTime(id, request);
	}

	@ApiOperation(value = "Delete Mass Time", response = BaseResponse.class)
	@DeleteMapping(value = ServiceEndpoints.ID)
	public BaseResponse<MassTimeDTO> deleteMassTime(@PathVariable("id") long id) {
		LOGGER.debug("Calling MassTimeController.deleteMassTime()");
		return service.deleteMassTime(id);
	}

	@ApiOperation(value = "Get Mass Time", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.ID)
	public BaseResponse<MassTimeDTO> getMassTime(@PathVariable("id") long id) {
		LOGGER.debug("Calling MassTimeController.getMassTime()");
		return service.getMassTime(id);
	}

	@ApiOperation(value = "Search List of Mass Time", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.SEARCH)
	public BaseResponsePage<MassTimeDTO> searchMassTime(MassTimeSerachSpec spec, Pageable pageable) {
		LOGGER.debug("Calling MassTimeController.getAllMassTime()");
		return service.searchMassTime(spec, pageable);
	}
}
