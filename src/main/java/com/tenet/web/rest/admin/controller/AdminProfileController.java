package com.tenet.web.rest.admin.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.admin.service.AdminProfileService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.dto.response.BaseResponsePage;
import com.tenet.web.rest.common.specification.ProfileSerachSpec;
import com.tenet.web.rest.profile.dto.ProfileDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Admin:Service")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_PROFILE)
public class AdminProfileController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private AdminProfileService service;

	@ApiOperation(value = "Update Profile Role", response = BaseResponse.class)
	@PutMapping(value = ServiceEndpoints.CHANGEROLE)
	public BaseResponse<ProfileDTO> changeRole(@PathVariable("profileid") Long id,
			@PathVariable("torolecode") String roleCode) {
		LOGGER.debug("Calling MassCoreTeamController.changeRole()");
		return service.changeRole(id, roleCode);
	}

	@ApiOperation(value = "Search Profile", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.SEARCH)
	public BaseResponsePage<ProfileDTO> searchProfile(ProfileSerachSpec spec, Pageable pageable) {
		LOGGER.debug("Calling MassCoreTeamController.searchProfile()");
		return service.searchProfile(spec, pageable);
	}
}
