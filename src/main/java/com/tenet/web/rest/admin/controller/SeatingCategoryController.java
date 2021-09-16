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

import com.tenet.web.rest.admin.dto.AllocationDTO;
import com.tenet.web.rest.admin.dto.AllocationRes;
import com.tenet.web.rest.admin.service.SeatingCategoryService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Admin:Seating Category")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_SEATING_CATEGORY)
public class SeatingCategoryController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private SeatingCategoryService service;

	@ApiOperation(value = "Update Seating Category", response = BaseResponse.class)
	@PutMapping(value = ServiceEndpoints.ID)
	public BaseResponse<AllocationRes> updateSeatingCategory(@PathVariable("id") Long id,
			@RequestBody AllocationDTO request) {
		LOGGER.debug("Calling SeatingCategoryController.updateSeatingCategory()");
		return service.updateSeatingCategory(id, request);
	}

	@ApiOperation(value = "Get All Seating Category", response = BaseResponse.class)
	@GetMapping
	public BaseResponse<AllocationRes> getAllSeatingCategory() {
		LOGGER.debug("Calling SeatingCategoryController.getAllSeatingCategory()");
		return service.getAllSeatingCategory();
	}
}
