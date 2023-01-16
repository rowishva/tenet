package com.tenet.web.rest.admin.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.dto.MarkAttendanceDTO;
import com.tenet.web.rest.admin.dto.MassTimeRes;
import com.tenet.web.rest.admin.service.AdminMassBookingService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.dto.response.BaseResponsePage;
import com.tenet.web.rest.common.entity.MassBooking;
import com.tenet.web.rest.common.entity.MassTime;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.MassBookingRepository;
import com.tenet.web.rest.common.repository.MassTimeRepository;
import com.tenet.web.rest.common.specification.MassBookingSerachSpec;
import com.tenet.web.rest.profile.dto.MassBookingRes;
import com.tenet.web.rest.profile.dto.MassBookingResponse;

@Service
public class AdminMassBookingSearviceImpl implements AdminMassBookingService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MassBookingRepository massBookingRepository;

	@Autowired
	private MassTimeRepository massTimeRepository;

	@Override
	public BaseResponsePage<MassBookingResponse> searchMassBooking(MassBookingSerachSpec spec, Pageable pageable) {
		LOGGER.debug("Calling AdminMassBookingSearviceImpl.searchMassBooking()");
		Page<MassBooking> massBookingPage = massBookingRepository.findAll(spec, pageable);
		BaseResponsePage<MassBookingResponse> response = new BaseResponsePage<MassBookingResponse>(
				HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
		if (massBookingPage != null) {
			List<MassBookingResponse> massBookingResponseList = new ArrayList<MassBookingResponse>();
			Map<Long, List<MassBooking>> massBookingMap = massBookingPage.stream()
					.collect(Collectors.groupingBy(MassBooking::getMassTimeId));
			Iterator<Map.Entry<Long, List<MassBooking>>> iterator = massBookingMap.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Long, List<MassBooking>> entry = iterator.next();
				Long massTimeId = entry.getKey();
				List<MassBooking> massBookings = entry.getValue();
				final MassTime massTime = massTimeRepository.findById(massTimeId)
						.orElseThrow(() -> new ResourceNotFoundException(
								ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + massTimeId));
				MassTimeRes massTimeDTO = modelMapper.map(massTime, MassTimeRes.class);
				String massBookingNo = massBookings.get(0).getMassBookingNo();
				List<MassBookingRes> massBookingResList = massBookings.stream()
						.map(massBooking -> modelMapper.map(massBooking, MassBookingRes.class))
						.collect(Collectors.toList());
				massBookingResponseList.add(new MassBookingResponse(massTimeDTO, massBookingNo, massBookingResList));
			}
			response.setResponseList(massBookingResponseList);
		}
		return response;
	}

	@Override
	public BaseResponse<String> markAttendance(MarkAttendanceDTO request) {
		LOGGER.debug("Calling AdminMassBookingSearviceImpl.markAttendance()");
		List<Long> attendanceList = request.getAttendanceList();
		List<MassBooking> massBookingList = new ArrayList<MassBooking>();
		for (Long massBookingId : attendanceList) {
			MassBooking massBooking = massBookingRepository.findById(massBookingId)
					.orElseThrow(() -> new ResourceNotFoundException(
							ApplicationConstants.ERROR_MSG_MASSBOOKING_NOT_FOUND + massBookingId));
			massBooking.setAttendance(true);
			massBookingList.add(massBooking);
		}
		massBookingRepository.saveAll(massBookingList);
		BaseResponse<String> response = new BaseResponse<String>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
		return response;
	}

}
