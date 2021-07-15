package com.tenet.web.rest.common.controller;

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
import com.tenet.web.rest.common.service.RoleService;
import com.tenet.web.rest.profile.dto.RoleDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Profile Role")
@RestController
@RequestMapping(ServiceEndpoints.PROFILE_ROLE)
public class RoleController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private RoleService service;

	@ApiOperation(value = "Create new Role", response = BaseResponse.class)
	@RequestMapping(value = ServiceEndpoints.REGISTER, method = RequestMethod.POST)
	public BaseResponse<RoleDTO> create(@RequestBody RoleDTO request) {
		LOGGER.debug("Calling RoleController.createRole()");
		return service.createRole(request);
	}

	@ApiOperation(value = "Update  Role", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.PUT)
	public BaseResponse<RoleDTO> update(@RequestBody RoleDTO request) {
		LOGGER.debug("Calling RoleController.updateRole()");
		return service.updateRole(request);
	}

	@ApiOperation(value = "Delete  Role", response = BaseResponse.class)
	@RequestMapping(value = ServiceEndpoints.ID, method = RequestMethod.DELETE)
	public BaseResponse<RoleDTO> delete(@PathVariable("id") long id) {
		LOGGER.debug("Calling RoleController.deleteRole()");
		return service.deleteRole(id);
	}

	@ApiOperation(value = "Get  Role", response = BaseResponse.class)
	@RequestMapping(value = ServiceEndpoints.ID, method = RequestMethod.GET)
	public BaseResponse<RoleDTO> get(@PathVariable("id") long id) {
		LOGGER.debug("Calling RoleController.getRole()");
		return service.getRole(id);
	}

	@ApiOperation(value = "Retrieve List of  Role", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.GET)
	public BaseResponse<RoleDTO> getAll() {
		LOGGER.debug("Calling RoleController.getAllRole()");
		return service.getAllRole();
	}

}
