package com.tenet.web.rest.admin.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.dto.ReportMassBookingDTO;
import com.tenet.web.rest.admin.service.ReportService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.entity.MassBooking;
import com.tenet.web.rest.common.entity.MassTime;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.MassBookingRepository;
import com.tenet.web.rest.common.repository.MassTimeRepository;
import com.tenet.web.rest.report.ReportExcelCreator;

@Service
public class ReportServiceImpl implements ReportService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MassTimeRepository massTimeRepository;

	@Autowired
	private MassBookingRepository massBookingRepository;

	@Override
	public void generateExcel(HttpServletResponse response, Long massTimeId) {
		LOGGER.debug("Calling ReportServiceImpl.generateExcel()");
		MassTime massTime = massTimeRepository.findById(massTimeId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + massTimeId));

		response.setContentType("application/vnd.ms-excel");
		StringBuilder fileName = new StringBuilder(massTime.getDate().toString());
		fileName.append("-");
		fileName.append(massTime.getTime());
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=Report_" + fileName.toString() + ".xlsx");

		List<MassBooking> massBookingList = massBookingRepository.findByMassTimeId(massTimeId);

		List<ReportMassBookingDTO> reportMassBookingDTOList = massBookingList.stream()
				.map(massBooking -> modelMapper.map(massBooking, ReportMassBookingDTO.class))
				.collect(Collectors.toList());

		Map<String, List<ReportMassBookingDTO>> reportMassBookingDTOMap = reportMassBookingDTOList.stream()
				.collect(Collectors.groupingBy(ReportMassBookingDTO::getMassBookingNo));

		ReportExcelCreator reportExcelCreator = new ReportExcelCreator(reportMassBookingDTOMap, fileName.toString());

		try {
			reportExcelCreator.export(response);
		} catch (IOException e) {
			new IOException();
		}

	}

}
