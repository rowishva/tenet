package com.tenet.web.rest.admin.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.admin.service.MassCoreTeamService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.MassCoreTeamDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Admin:Mass Core Team")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_MASS_CORE_TEAM)
public class MassCoreTeamController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private MassCoreTeamService service;

	@ApiOperation(value = "Create new List of Mass Core Team", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.POST)
	public BaseResponse<MassCoreTeamDTO> createMassCoreTeam(@PathVariable("massTimeId") Long massTimeId,
			@RequestBody List<MassCoreTeamDTO> request) {
		LOGGER.debug("Calling MassCoreTeamController.createMassCoreTeam()");
		return service.createMassCoreTeam(massTimeId, request);
	}

	@ApiOperation(value = "Update List of Mass Core Team", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.PUT)
	public BaseResponse<MassCoreTeamDTO> updateMassCoreTeam(@PathVariable("massTimeId") Long massTimeId,
			@RequestBody List<MassCoreTeamDTO> request) {
		LOGGER.debug("Calling MassCoreTeamController.updateMassCoreTeam()");
		return service.updateMassCoreTeam(massTimeId, request);
	}

	@ApiOperation(value = "Retrieve List of Mass Core Team", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.GET)
	public BaseResponse<MassCoreTeamDTO> getAllMassCoreTeam(@PathVariable("massTimeId") Long massTimeId) {
		LOGGER.debug("Calling MassCoreTeamController.getAllMassCoreTeam()");
		return service.getAllMassCoreTeam(massTimeId);
	}
}
