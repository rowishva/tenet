package com.tenet.web.rest.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.dto.WalkInMassBookingDTO;
import com.tenet.web.rest.admin.service.WalkInMassBookingService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.MassBooking;
import com.tenet.web.rest.common.entity.MassBookingCategory;
import com.tenet.web.rest.common.entity.MassTime;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.MassBookingCategoryRepository;
import com.tenet.web.rest.common.repository.MassBookingRepository;
import com.tenet.web.rest.common.repository.MassTimeRepository;

@Service
public class WalkInMassBookingServiceImpl implements WalkInMassBookingService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MassTimeRepository massTimeRepository;

	@Autowired
	private MassBookingCategoryRepository massBookingCategoryRepository;

	@Autowired
	private MassBookingRepository massBookingRepository;

	@Override
	public BaseResponse<WalkInMassBookingDTO> createWalkInMassBooking(Long massTimeId, WalkInMassBookingDTO request) {
		LOGGER.debug("Calling WalkInMassBookingServiceImpl.createWalkInMassBooking()");
		final MassTime massTime = massTimeRepository.findById(massTimeId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + massTimeId));

		MassBookingCategory massBookingCategory = massBookingCategoryRepository.findByTagAndMassTime("PUBLIC",
				massTime);
		int availableCapacity = massBookingCategory.getAvailableCapacity();
		if (!(availableCapacity > 0)) {
			new ResourceNotFoundException(
					ApplicationConstants.ERROR_MSG_FULLY_BOOKED_MASS_TIME_TAG + massTimeId + " PUBLIC");
		}

		MassBooking massBooking = massBookingRepository.findByMassTimeIdandTagLimit1(massTimeId, "PUBLIC");
		availableCapacity--;
		massBookingCategory.setAvailableCapacity(availableCapacity);
		massBookingCategoryRepository.save(massBookingCategory);

		massBooking.setFullName(request.getFullName());
		massBooking.setContactNumber(request.getContactNumber());
		massBooking.setMassBookingNo(ApplicationConstants.WALK_IN);
		massBooking.setBooked(true);
		if (request.isAttendance()) {
			massBooking.setAttendance(request.isAttendance());
		}
		massBookingRepository.save(massBooking);

		WalkInMassBookingDTO walkInMassBookingDTO = modelMapper.map(massBooking, WalkInMassBookingDTO.class);
		BaseResponse<WalkInMassBookingDTO> response = new BaseResponse<WalkInMassBookingDTO>(HttpStatus.CREATED.value(),
				ApplicationConstants.SUCCESS, walkInMassBookingDTO);
		return response;
	}

	@Override
	public BaseResponse<WalkInMassBookingDTO> updateWalkInMassBooking(Long massBookingId,
			WalkInMassBookingDTO request) {
		LOGGER.debug("Calling WalkInMassBookingServiceImpl.updateWalkInMassBooking()");
		MassBooking massBooking = massBookingRepository.findById(massBookingId)
				.orElseThrow(() -> new ResourceNotFoundException(
						ApplicationConstants.ERROR_MSG_MASSCORETEAM_NOT_FOUND + massBookingId));

		massBooking.setFullName(request.getFullName());
		massBooking.setContactNumber(request.getContactNumber());
		massBookingRepository.save(massBooking);
		WalkInMassBookingDTO walkInMassBookingDTO = modelMapper.map(massBooking, WalkInMassBookingDTO.class);
		BaseResponse<WalkInMassBookingDTO> response = new BaseResponse<WalkInMassBookingDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, walkInMassBookingDTO);
		return response;
	}

	@Override
	public BaseResponse<WalkInMassBookingDTO> deleteWalkInMassBooking(Long massBookingId) {
		LOGGER.debug("Calling WalkInMassBookingServiceImpl.deleteWalkInMassBooking()");
		MassBooking massBooking = massBookingRepository.findById(massBookingId)
				.orElseThrow(() -> new ResourceNotFoundException(
						ApplicationConstants.ERROR_MSG_MASSCORETEAM_NOT_FOUND + massBookingId));

		massBooking.setFullName("");
		massBooking.setContactNumber("");
		massBooking.setBooked(false);
		massBooking.setAttendance(false);
		massBookingRepository.save(massBooking);
		BaseResponse<WalkInMassBookingDTO> response = new BaseResponse<WalkInMassBookingDTO>(
				HttpStatus.NO_CONTENT.value(), ApplicationConstants.SUCCESS);
		return response;
	}

	@Override
	public BaseResponse<WalkInMassBookingDTO> getAllWalkInMassBooking(Long massTimeId) {
		LOGGER.debug("Calling WalkInMassBookingServiceImpl.getAllWalkInMassBooking()");
		List<MassBooking> massBookingList = massBookingRepository.findByWalkInList(massTimeId);
		BaseResponse<WalkInMassBookingDTO> response = new BaseResponse<WalkInMassBookingDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		if (massBookingList != null && massBookingList.size() > 0) {
			List<WalkInMassBookingDTO> walkInMassBookingDTOList = massBookingList.stream()
					.map(massCoreTeam -> modelMapper.map(massCoreTeam, WalkInMassBookingDTO.class))
					.collect(Collectors.toList());
			response.setResponseList(walkInMassBookingDTOList);
		}
		return response;
	}

}
