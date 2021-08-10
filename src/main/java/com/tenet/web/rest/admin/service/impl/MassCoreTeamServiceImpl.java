package com.tenet.web.rest.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.service.MassCoreTeamService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.MassCoreTeam;
import com.tenet.web.rest.common.entity.MassTime;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.MassCoreTeamRepository;
import com.tenet.web.rest.common.repository.MassTimeRepository;
import com.tenet.web.rest.profile.dto.MassCoreTeamDTO;

@Service
public class MassCoreTeamServiceImpl implements MassCoreTeamService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MassCoreTeamRepository massCoreTeamRepository;

	@Autowired
	private MassTimeRepository massTimeRepository;

	@Override
	public BaseResponse<MassCoreTeamDTO> createMassCoreTeam(Long massTimeId, MassCoreTeamDTO request) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.createMassCoreTeam()");
		final MassTime massTime = massTimeRepository.findById(massTimeId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + massTimeId));		
		
		MassCoreTeam massCoreTeam = modelMapper.map(request, MassCoreTeam.class);
		massCoreTeam.setMassTime(massTime);
		massCoreTeam = massCoreTeamRepository.save(massCoreTeam);
		
		MassCoreTeamDTO massCoreTeamDTO = modelMapper.map(massCoreTeam, MassCoreTeamDTO.class);
		BaseResponse<MassCoreTeamDTO> response = new BaseResponse<MassCoreTeamDTO>(HttpStatus.CREATED.value(),
				ApplicationConstants.SUCCESS, massCoreTeamDTO);
		return response;
	}

	@Override
	public BaseResponse<MassCoreTeamDTO> updateMassCoreTeam(Long massTimeId, Long massCoreTeamId, MassCoreTeamDTO request) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.updateMassCoreTeam()");
		MassCoreTeam massCoreTeam = massCoreTeamRepository.findById(massTimeId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSCORETEAM_NOT_FOUND + massCoreTeamId));
		
		modelMapper.map(request, massCoreTeam);		
		massCoreTeam = massCoreTeamRepository.save(massCoreTeam);
		MassCoreTeamDTO massCoreTeamDTO = modelMapper.map(massCoreTeam, MassCoreTeamDTO.class);
		BaseResponse<MassCoreTeamDTO> response = new BaseResponse<MassCoreTeamDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, massCoreTeamDTO);
		return response;
	}
	
	@Override
	public BaseResponse<MassCoreTeamDTO> deleteMassCoreTeam(Long massTimeId, Long massCoreTeamId) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.updateMassCoreTeam()");
		MassCoreTeam massCoreTeam = massCoreTeamRepository.findById(massCoreTeamId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSCORETEAM_NOT_FOUND + massCoreTeamId));
		
		massCoreTeam.setDeleted(true);	
		massCoreTeamRepository.save(massCoreTeam);
		BaseResponse<MassCoreTeamDTO> response = new BaseResponse<MassCoreTeamDTO>(HttpStatus.NO_CONTENT.value(),
				ApplicationConstants.SUCCESS);
		return response;
	}
	
	@Override
	public BaseResponse<MassCoreTeamDTO> getMassCoreTeam(Long massTimeId, Long massCoreTeamId) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.getAllMassCoreTeam()");
		MassCoreTeam massCoreTeam = massCoreTeamRepository.findById(massCoreTeamId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSCORETEAM_NOT_FOUND + massCoreTeamId));
		MassCoreTeamDTO massCoreTeamDTO = modelMapper.map(massCoreTeam, MassCoreTeamDTO.class);
		BaseResponse<MassCoreTeamDTO> response = new BaseResponse<MassCoreTeamDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, massCoreTeamDTO);		
		return response;
	}

	@Override
	public BaseResponse<MassCoreTeamDTO> getAllMassCoreTeam(Long massTimeId) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.getAllMassCoreTeam()");
		List<MassCoreTeam> massCoreTeamList = massCoreTeamRepository.findByMassTimeId(massTimeId);
		BaseResponse<MassCoreTeamDTO> response = new BaseResponse<MassCoreTeamDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		if (massCoreTeamList != null && massCoreTeamList.size() > 0) {
			List<MassCoreTeamDTO> massCoreTeamDTOList = massCoreTeamList.stream()
					.map(massCoreTeam -> modelMapper.map(massCoreTeam, MassCoreTeamDTO.class))
					.collect(Collectors.toList());
			response.setResponseList(massCoreTeamDTOList);
		}
		return response;
	}

}
