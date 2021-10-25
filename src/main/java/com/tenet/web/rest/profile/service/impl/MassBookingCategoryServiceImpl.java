package com.tenet.web.rest.profile.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.MassBookingCategory;
import com.tenet.web.rest.common.entity.MassTime;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.MassBookingCategoryRepository;
import com.tenet.web.rest.common.repository.MassTimeRepository;
import com.tenet.web.rest.profile.dto.MassBookingCategoryDTO;
import com.tenet.web.rest.profile.service.MassBookingCategoryService;

@Service
public class MassBookingCategoryServiceImpl implements MassBookingCategoryService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MassBookingCategoryRepository massBookingCategoryRepository;

	@Autowired
	private MassTimeRepository massTimeRepository;

	@Override
	public BaseResponse<MassBookingCategoryDTO> getMassBookingCategory(long massTimeId) {

		LOGGER.debug("Calling MassBookingCategoryServiceImpl.getMassBookingCategory()");
		final MassTime massTime = massTimeRepository.findById(massTimeId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + massTimeId));

		MassBookingCategory massBookingCategory = massBookingCategoryRepository.findByTagAndMassTime("PUBLIC",
				massTime);

		MassBookingCategoryDTO massBookingCategoryDTO = modelMapper.map(massBookingCategory,
				MassBookingCategoryDTO.class);
		BaseResponse<MassBookingCategoryDTO> response = new BaseResponse<MassBookingCategoryDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, massBookingCategoryDTO);
		return response;
	}

	@Override
	public BaseResponse<MassBookingCategoryDTO> getAdminMassBookingCategory(long massTimeId) {
		LOGGER.debug("Calling MassBookingCategoryServiceImpl.getAdminMassBookingCategory()");
		final MassTime massTime = massTimeRepository.findById(massTimeId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + massTimeId));

		List<MassBookingCategory> massBookingCategoryList = massBookingCategoryRepository.findByMassTime(massTime);

		BaseResponse<MassBookingCategoryDTO> response = new BaseResponse<MassBookingCategoryDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		if (massBookingCategoryList != null && massBookingCategoryList.size() > 0) {
			List<MassBookingCategoryDTO> massBookingCategoryDTOList = massBookingCategoryList.stream()
					.map(massBookingCategory -> modelMapper.map(massBookingCategory, MassBookingCategoryDTO.class))
					.collect(Collectors.toList());
			response.setResponseList(massBookingCategoryDTOList);
		}
		return response;
	}
}
