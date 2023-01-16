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

import com.tenet.web.rest.admin.dto.GlobalParameterDTO;
import com.tenet.web.rest.admin.service.GlobalParameterService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Admin: Global Parameter")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_GLOBAL_PARAMETER)
public class GlobalParameterController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private GlobalParameterService service;

	@ApiOperation(value = "Update  Global Parameter")
	@PutMapping(value = ServiceEndpoints.ID)
	public BaseResponse<GlobalParameterDTO> updateGlobalParameter(@PathVariable("id") long id,
			@RequestBody GlobalParameterDTO request) {
		LOGGER.debug("Calling GlobalParameterController.updateGlobalParameter()");
		return service.updateGlobalParameter(id, request);
	}

	@ApiOperation(value = "Retrieve All Global Paramete")
	@GetMapping
	public BaseResponse<GlobalParameterDTO> getAllGlobalParameter() {
		LOGGER.debug("Calling GlobalParameterController.getAllGlobalParameter()");
		return service.getAllGlobalParameter();
	}

}
