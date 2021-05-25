package com.tenet.web.rest.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.admin.dto.AdminUserDTO;
import com.tenet.web.rest.admin.service.AdminService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "BackOffice")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_USER)
public class AdminController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private AdminService service;

	@ApiOperation(value = "Create new Admin User", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.POST)
	public BaseResponse<AdminUserDTO> createAdminUser(@RequestBody AdminUserDTO request) {
		LOGGER.debug("Calling AdminController.createAdminUser()");
		return service.createAdminUser(request);
	}

	@ApiOperation(value = "Update Admin User", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.PUT)
	public BaseResponse<AdminUserDTO> updateAdminUser(@RequestBody AdminUserDTO request) {
		LOGGER.debug("Calling AdminController.updateAdminUser()");
		return service.updateAdminUser(request);
	}

	@ApiOperation(value = "Delete Admin User", response = BaseResponse.class)
	@RequestMapping(value = ServiceEndpoints.ID, method = RequestMethod.DELETE)
	public BaseResponse<AdminUserDTO> deleteAdminUser(@PathVariable("id") long id) {
		LOGGER.debug("Calling AdminController.deleteAdminUser()");
		return service.deleteAdminUser(id);
	}

	@ApiOperation(value = "Get Admin User", response = BaseResponse.class)
	@RequestMapping(value = ServiceEndpoints.ID, method = RequestMethod.GET)
	public BaseResponse<AdminUserDTO> getAdminUser(@PathVariable("id") long id) {
		LOGGER.debug("Calling AdminController.getAdminUser()");
		return service.getAdminUser(id);
	}

	@ApiOperation(value = "Retrieve List of Admin User", response = BaseResponse.class)
	@RequestMapping(method = RequestMethod.GET)
	public BaseResponse<AdminUserDTO> getAllAdminUser() {
		LOGGER.debug("Calling AdminController.getAllAdminUser()");
		return service.getAllAdminUser();
	}

}
