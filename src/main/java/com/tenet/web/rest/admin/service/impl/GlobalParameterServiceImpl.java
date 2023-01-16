package com.tenet.web.rest.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.dto.GlobalParameterDTO;
import com.tenet.web.rest.admin.service.GlobalParameterService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.GlobalParameter;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.GlobalParameterRepository;

@Service
public class GlobalParameterServiceImpl implements GlobalParameterService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private GlobalParameterRepository globalParameterRepository;

	@Override
	public BaseResponse<GlobalParameterDTO> updateGlobalParameter(Long id, GlobalParameterDTO request) {
		LOGGER.debug("Calling GlobalParameterServiceImpl.updateGlobalParameter()");
		GlobalParameter globalParameter = globalParameterRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_GLOBALPARAMETER_NOT_FOUND + id));

		modelMapper.map(request, globalParameter);
		globalParameter = globalParameterRepository.save(globalParameter);
		GlobalParameterDTO globalParameterDTO = modelMapper.map(globalParameter, GlobalParameterDTO.class);
		BaseResponse<GlobalParameterDTO> response = new BaseResponse<GlobalParameterDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, globalParameterDTO);
		return response;
	}

	@Override
	public BaseResponse<GlobalParameterDTO> getAllGlobalParameter() {
		LOGGER.debug("Calling GlobalParameterServiceImpl.getAllGlobalParameter()");
		List<GlobalParameter> globalParameterList = globalParameterRepository.findAll();

		List<GlobalParameterDTO> globalParameterDTOList = globalParameterList.stream()
				.map(globalParameter -> modelMapper.map(globalParameter, GlobalParameterDTO.class))
				.collect(Collectors.toList());
		BaseResponse<GlobalParameterDTO> response = new BaseResponse<GlobalParameterDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, globalParameterDTOList);
		return response;
	}
}
