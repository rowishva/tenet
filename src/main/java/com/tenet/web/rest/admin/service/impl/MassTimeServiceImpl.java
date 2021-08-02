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
import com.tenet.web.rest.common.entity.MassTime;
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
		MassTime massTime = massTimeRepository.getOne(id);
		if (massTime == null) {
			return new BaseResponse<MassTimeDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
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
		MassTime massTime = massTimeRepository.getOne(id);
		BaseResponse<MassTimeDTO> response = null;
		if (massTime != null) {
			massTime.setDeleted(true);
			massTime = massTimeRepository.save(massTime);
			response = new BaseResponse<MassTimeDTO>(HttpStatus.NO_CONTENT.value(), ApplicationConstants.SUCCESS);
		} else {
			response = new BaseResponse<MassTimeDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<MassTimeDTO> getMassTime(Long id) {
		LOGGER.debug("Calling MassTimeServiceImpl.getMassTime()");
		MassTime massTime = massTimeRepository.getOne(id);
		BaseResponse<MassTimeDTO> response = null;
		if (massTime != null) {
			MassTimeDTO massTimeDTO = modelMapper.map(massTime, MassTimeDTO.class);
			response = new BaseResponse<MassTimeDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS, massTimeDTO);
		} else {
			response = new BaseResponse<MassTimeDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<MassTimeDTO> getAllMassTime(Pageable pageable) {
		LOGGER.debug("Calling MassTimeServiceImpl.getAllMassTime()");
		Page<MassTime> massTimeList = massTimeRepository.findAll(pageable);
		BaseResponse<MassTimeDTO> response = null;
		if (massTimeList != null) {
			List<MassTimeDTO> massTimeDTOList = massTimeList.stream()
					.map(massTime -> modelMapper.map(massTime, MassTimeDTO.class)).collect(Collectors.toList());
			response = new BaseResponse<MassTimeDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS,
					massTimeDTOList);
		} else {
			response = new BaseResponse<MassTimeDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

}
