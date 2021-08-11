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

import com.tenet.web.rest.admin.dto.CommunityAllocationDTO;
import com.tenet.web.rest.admin.dto.CommunityAllocationRes;
import com.tenet.web.rest.admin.service.CommunityAllocationService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Admin:Community Allocation")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_COMMUNITY_ALLOCATION)
public class CommunityAllocationController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private CommunityAllocationService service;

	@ApiOperation(value = "Update Community Allocation", response = BaseResponse.class)
	@PutMapping(value = ServiceEndpoints.ID)
	public BaseResponse<CommunityAllocationRes> updateCommunityAllocation(@PathVariable("id") Long id,
			@RequestBody CommunityAllocationDTO request) {
		LOGGER.debug("Calling MassCoreTeamController.updateCommunityAllocation()");
		return service.updateCommunityAllocation(id, request);
	}

	@ApiOperation(value = "Get All Community Allocation", response = BaseResponse.class)
	@GetMapping
	public BaseResponse<CommunityAllocationRes> getAllCommunityAllocation() {
		LOGGER.debug("Calling MassCoreTeamController.getAllCommunityAllocation()");
		return service.getAllCommunityAllocation();
	}
}
