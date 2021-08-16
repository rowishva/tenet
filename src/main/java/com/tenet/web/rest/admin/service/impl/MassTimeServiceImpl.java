package com.tenet.web.rest.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.dto.MassTimeDTO;
import com.tenet.web.rest.admin.service.MassTimeService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.dto.response.BaseResponsePage;
import com.tenet.web.rest.common.entity.MassTime;
import com.tenet.web.rest.common.exception.ResourceAlreadyExistsException;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.MassTimeRepository;
import com.tenet.web.rest.common.specification.MassTimeSerachSpec;

@Service
public class MassTimeServiceImpl implements MassTimeService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MassTimeRepository massTimeRepository;

	@Override
	public BaseResponse<MassTimeDTO> createMassTime(MassTimeDTO request) {

		LOGGER.debug("Calling MassTimeServiceImpl.createMassTime()");
		long duplicateCount = massTimeRepository.countByDateAndTime(request.getDate(), request.getTime());
		if (duplicateCount > 0) {
			throw new ResourceAlreadyExistsException(
					ApplicationConstants.ERROR_MSG_MASSTIME_FOUND + request.getDate() + request.getTime());
		}
		MassTime massTime = modelMapper.map(request, MassTime.class);
		massTime.setAvailableCapacity(request.getTotalCapacity());
		massTime = massTimeRepository.save(massTime);
		MassTimeDTO massTimeDTO = modelMapper.map(massTime, MassTimeDTO.class);
		BaseResponse<MassTimeDTO> response = new BaseResponse<MassTimeDTO>(HttpStatus.CREATED.value(),
				ApplicationConstants.SUCCESS, massTimeDTO);
		return response;
	}

	@Override
	public BaseResponse<MassTimeDTO> updateMassTime(Long id, MassTimeDTO request) {

		LOGGER.debug("Calling MassTimeServiceImpl.updateMassTime()");
		MassTime massTime = massTimeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + id));
		modelMapper.map(request, massTime);
		massTime = massTimeRepository.save(massTime);
		MassTimeDTO massTimeDTO = modelMapper.map(massTime, MassTimeDTO.class);
		BaseResponse<MassTimeDTO> response = new BaseResponse<MassTimeDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, massTimeDTO);
		return response;
	}

	@Override
	public BaseResponse<MassTimeDTO> deleteMassTime(Long id) {
		LOGGER.debug("Calling MassTimeServiceImpl.deleteMassTime()");
		MassTime massTime = massTimeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + id));
		massTime.setDeleted(true);
		massTime = massTimeRepository.save(massTime);
		BaseResponse<MassTimeDTO> response = new BaseResponse<MassTimeDTO>(HttpStatus.NO_CONTENT.value(),
				ApplicationConstants.SUCCESS);
		return response;
	}

	@Override
	public BaseResponse<MassTimeDTO> getMassTime(Long id) {
		LOGGER.debug("Calling MassTimeServiceImpl.getMassTime()");
		MassTime massTime = massTimeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + id));
		BaseResponse<MassTimeDTO> response = new BaseResponse<MassTimeDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		if (massTime != null) {
			MassTimeDTO massTimeDTO = modelMapper.map(massTime, MassTimeDTO.class);
			response.setResponse(massTimeDTO);
		}
		return response;
	}

	@Override
	public BaseResponsePage<MassTimeDTO> searchMassTime(MassTimeSerachSpec spec, Pageable pageable) {
		Page<MassTime> massTimePage = massTimeRepository.findAll(spec, pageable);
		BaseResponsePage<MassTimeDTO> response = new BaseResponsePage<MassTimeDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		if (massTimePage != null) {
			List<MassTimeDTO> massTimeDTOList = massTimePage.stream()
					.map(massTime -> modelMapper.map(massTime, MassTimeDTO.class)).collect(Collectors.toList());
			response.setResponseList(massTimeDTOList);
			response.setTotalElements(massTimePage.getTotalElements());
			response.setTotalPages(massTimePage.getTotalPages());
		}
		return response;
	}

}
