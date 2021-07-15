package com.tenet.web.rest.profile.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.DependentDTO;
import com.tenet.web.rest.profile.service.DependentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "ProfileDependent")
@RestController
@RequestMapping(ServiceEndpoints.PROFILE_DEPENDENT)
public class DependentController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private DependentService service;

	@ApiOperation(value = "Create new List of Dependent", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.POST)
	public BaseResponse<DependentDTO> createDependents(@PathVariable("profileId") Long profileId,
			@RequestBody List<DependentDTO> request) {
		LOGGER.debug("Calling DependentController.createDependents()");
		return service.createDependents(profileId, request);
	}

	@ApiOperation(value = "Update Profile", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.PUT)
	public BaseResponse<DependentDTO> updateDependents(@PathVariable("profileId") Long profileId,
			@RequestBody List<DependentDTO> request) {
		LOGGER.debug("Calling DependentController.updateDependents()");
		return service.updateDependents(profileId, request);
	}

	@ApiOperation(value = "Retrieve List of Dependent", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.GET)
	public BaseResponse<DependentDTO> getDependents(@PathVariable("profileId") Long profileId) {
		LOGGER.debug("Calling DependentController.getDependents()");
		return service.getDependents(profileId);
	}
}
