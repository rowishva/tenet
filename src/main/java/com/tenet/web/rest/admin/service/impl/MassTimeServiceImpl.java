package com.tenet.web.rest.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.dto.MassTimeDTO;
import com.tenet.web.rest.admin.service.MassTimeService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.MassTime;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.MassTimeRepository;

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
	public BaseResponse<MassTimeDTO> getAllMassTime(Integer pageNo, Integer pageSize, String sortBy, String direction) {
		LOGGER.debug("Calling MassTimeServiceImpl.getAllMassTime()");
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		if (direction.equals("ASC")) {
			pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
		}
		Page<MassTime> massTimeList = massTimeRepository.findAll(pageable);
		BaseResponse<MassTimeDTO> response = new BaseResponse<MassTimeDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		if (massTimeList != null) {
			List<MassTimeDTO> massTimeDTOList = massTimeList.stream()
					.map(massTime -> modelMapper.map(massTime, MassTimeDTO.class)).collect(Collectors.toList());
			response.setResponseList(massTimeDTOList);
		}
		return response;
	}

}
