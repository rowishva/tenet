package com.tenet.web.rest.profile.controller;

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

	@ApiOperation(value = "Create new Dependent", response = BaseResponse.class)
	@PostMapping
	public BaseResponse<DependentDTO> createDependents(@PathVariable("profileId") Long profileId,
			@RequestBody DependentDTO request) {
		LOGGER.debug("Calling DependentController.createDependents()");
		return service.createDependent(profileId, request);
	}

	@ApiOperation(value = "Update Dependent", response = BaseResponse.class)
	@PutMapping(value = ServiceEndpoints.DEPENDENT_ID)
	public BaseResponse<DependentDTO> updateDependents(@PathVariable("profileId") Long profileId,
			@PathVariable("dependentId") Long dependentId, @RequestBody DependentDTO request) {
		LOGGER.debug("Calling DependentController.updateDependents()");
		return service.updateDependent(profileId, dependentId, request);
	}

	@ApiOperation(value = "Delete Dependent", response = BaseResponse.class)
	@DeleteMapping(value = ServiceEndpoints.DEPENDENT_ID)
	public BaseResponse<DependentDTO> deleteDependents(@PathVariable("profileId") Long profileId,
			@PathVariable("dependentId") Long dependentId) {
		LOGGER.debug("Calling DependentController.deleteDependents()");
		return service.deleteDependent(profileId, dependentId);
	}

	@ApiOperation(value = "Get Dependent", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.DEPENDENT_ID)
	public BaseResponse<DependentDTO> getDependents(@PathVariable("profileId") Long profileId,
			@PathVariable("dependentId") Long dependentId) {
		LOGGER.debug("Calling DependentController.getDependents()");
		return service.getDependent(profileId, dependentId);
	}

	@ApiOperation(value = "Get All Dependents", response = BaseResponse.class)
	@GetMapping
	public BaseResponse<DependentDTO> getDependents(@PathVariable("profileId") Long profileId) {
		LOGGER.debug("Calling DependentController.getAllDependents()");
		return service.getAllDependents(profileId);
	}
}
