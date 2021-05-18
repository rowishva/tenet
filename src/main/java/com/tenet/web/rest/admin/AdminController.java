package com.tenet.web.rest.admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.admin.dto.request.AdminUserDTO;
import com.tenet.web.rest.admin.service.AdminService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;

import io.swagger.annotations.Api;

@Api(value = "AdminController")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_USER)
public class AdminController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private AdminService service;

	@RequestMapping(method = RequestMethod.POST)
	public BaseResponse createAdminUser(@RequestBody AdminUserDTO request) {
		LOGGER.debug("Calling AdminController.createAdminUser()");
		return service.createAdminUser(request);
	}

}
