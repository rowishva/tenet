package com.tenet.web.rest.admin.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.admin.service.ReportService;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Admin:Report Excel")
@RestController
@RequestMapping(ServiceEndpoints.ADMIN_REPORT_EXCEL)
public class ReportController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ReportService service;

	@ApiOperation(value = "Generate Excel Report", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.MASSTIME_ID)
	public void generateExcel(HttpServletResponse response, @PathVariable("massTimeId") Long massTimeId) {
		LOGGER.debug("Calling ReportController.generateExcel()");
		service.generateExcel(response, massTimeId);
	}
}
