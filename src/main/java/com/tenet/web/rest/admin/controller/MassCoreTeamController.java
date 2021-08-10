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

	@ApiOperation(value = "Create new Mass Core Team", response = BaseResponse.class)
	@PostMapping
	public BaseResponse<MassCoreTeamDTO> createMassCoreTeam(@PathVariable("massTimeId") Long massTimeId,
			@RequestBody MassCoreTeamDTO request) {
		LOGGER.debug("Calling MassCoreTeamController.createMassCoreTeam()");
		return service.createMassCoreTeam(massTimeId, request);
	}

	@ApiOperation(value = "Update Mass Core Team", response = BaseResponse.class)
	@PutMapping(value = ServiceEndpoints.MASSCORETEAM_ID)
	public BaseResponse<MassCoreTeamDTO> updateMassCoreTeam(@PathVariable("massTimeId") Long massTimeId,
			@PathVariable("massCoreTeamId") Long massCoreTeamId, @RequestBody MassCoreTeamDTO request) {
		LOGGER.debug("Calling MassCoreTeamController.updateMassCoreTeam()");
		return service.updateMassCoreTeam(massTimeId, massCoreTeamId, request);
	}

	@ApiOperation(value = "Delete Mass Core Team", response = BaseResponse.class)
	@DeleteMapping(value = ServiceEndpoints.MASSCORETEAM_ID)
	public BaseResponse<MassCoreTeamDTO> deleteMassCoreTeam(@PathVariable("massTimeId") Long massTimeId,
			@PathVariable("massCoreTeamId") Long massCoreTeamId) {
		LOGGER.debug("Calling MassCoreTeamController.deleteMassCoreTeam()");
		return service.deleteMassCoreTeam(massTimeId, massCoreTeamId);
	}

	@ApiOperation(value = "Get Mass Core Team", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.MASSCORETEAM_ID)
	public BaseResponse<MassCoreTeamDTO> getMassCoreTeam(@PathVariable("massTimeId") Long massTimeId,
			@PathVariable("massCoreTeamId") Long massCoreTeamId) {
		LOGGER.debug("Calling MassCoreTeamController.getMassCoreTeam()");
		return service.getMassCoreTeam(massTimeId, massCoreTeamId);
	}

	@ApiOperation(value = "Get All Mass Core Team", response = BaseResponse.class)
	@GetMapping
	public BaseResponse<MassCoreTeamDTO> getAllMassCoreTeam(@PathVariable("massTimeId") Long massTimeId) {
		LOGGER.debug("Calling MassCoreTeamController.getAllMassCoreTeam()");
		return service.getAllMassCoreTeam(massTimeId);
	}
}
