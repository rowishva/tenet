package com.tenet.web.rest.admin.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.admin.dto.MassTimeDTO;
import com.tenet.web.rest.admin.service.MassTimeService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;

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
	@RequestMapping(method = RequestMethod.POST)
	public BaseResponse<MassTimeDTO> createMassTime(@RequestBody MassTimeDTO request) {
		LOGGER.debug("Calling MassTimeController.createMassTime()");
		return service.createMassTime(request);
	}

	@ApiOperation(value = "Update Mass Time", response = BaseResponse.class)
	@RequestMapping(value = ServiceEndpoints.ID, method = RequestMethod.PUT)
	public BaseResponse<MassTimeDTO> updateMassTime(@PathVariable("id") long id, @RequestBody MassTimeDTO request) {
		LOGGER.debug("Calling MassTimeController.updateMassTime()");
		return service.updateMassTime(id, request);
	}

	@ApiOperation(value = "Delete Mass Time", response = BaseResponse.class)
	@RequestMapping(value = ServiceEndpoints.ID, method = RequestMethod.DELETE)
	public BaseResponse<MassTimeDTO> deleteMassTime(@PathVariable("id") long id) {
		LOGGER.debug("Calling MassTimeController.deleteMassTime()");
		return service.deleteMassTime(id);
	}

	@ApiOperation(value = "Get Mass Time", response = BaseResponse.class)
	@RequestMapping(value = ServiceEndpoints.ID, method = RequestMethod.GET)
	public BaseResponse<MassTimeDTO> getMassTime(@PathVariable("id") long id) {
		LOGGER.debug("Calling MassTimeController.getMassTime()");
		return service.getMassTime(id);
	}

	@ApiOperation(value = "Retrieve List of Mass Time", response = BaseResponse.class)
	@RequestMapping(value = ServiceEndpoints.PAGE, method = RequestMethod.GET)
	public BaseResponse<MassTimeDTO> getAllMassTime(@PathVariable("pageNo") Integer pageNo,
			@PathVariable("pageSize") Integer pageSize) {
		LOGGER.debug("Calling MassTimeController.getAllMassTime()");
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return service.getAllMassTime(pageable);
	}
}
