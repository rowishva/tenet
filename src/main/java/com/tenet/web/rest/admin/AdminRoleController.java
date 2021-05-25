package com.tenet.web.rest.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.admin.dto.AdminUserRoleDTO;
import com.tenet.web.rest.admin.service.AdminRoleService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "BackOffice")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_ROLE)
public class AdminRoleController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private AdminRoleService service;

	@ApiOperation(value = "Create new Admin Role", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.POST)
	public BaseResponse<AdminUserRoleDTO> createAdminUser(@RequestBody AdminUserRoleDTO request) {
		LOGGER.debug("Calling AdminRoleController.createAdminUserRole()");
		return service.createAdminUserRole(request);
	}

	@ApiOperation(value = "Update Admin Role", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.PUT)
	public BaseResponse<AdminUserRoleDTO> updateAdminUser(@RequestBody AdminUserRoleDTO request) {
		LOGGER.debug("Calling AdminRoleController.updateAdminUserRole()");
		return service.updateAdminUserRole(request);
	}

	@ApiOperation(value = "Delete Admin Role", response = BaseResponse.class)
	@RequestMapping(value = ServiceEndpoints.ID, method = RequestMethod.DELETE)
	public BaseResponse<AdminUserRoleDTO> deleteAdminUser(@PathVariable("id") long id) {
		LOGGER.debug("Calling AdminRoleController.deleteAdminUserRole()");
		return service.deleteAdminUserRole(id);
	}

	@ApiOperation(value = "Get Admin Role", response = BaseResponse.class)
	@RequestMapping(value = ServiceEndpoints.ID, method = RequestMethod.GET)
	public BaseResponse<AdminUserRoleDTO> getAdminUser(@PathVariable("id") long id) {
		LOGGER.debug("Calling AdminRoleController.getAdminUserRole()");
		return service.getAdminUserRole(id);
	}

	@ApiOperation(value = "Retrieve List of Admin Role", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.GET)
	public BaseResponse<AdminUserRoleDTO> getAllAdminUser() {
		LOGGER.debug("Calling AdminRoleController.getAllAdminUserRole()");
		return service.getAllAdminUserRole();
	}

}
