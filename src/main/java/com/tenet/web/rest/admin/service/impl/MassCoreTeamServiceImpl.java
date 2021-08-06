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
	public BaseResponse<MassCoreTeamDTO> createMassCoreTeam(Long massTimeId, List<MassCoreTeamDTO> request) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.createMassCoreTeam()");
		final MassTime massTime = massTimeRepository.findById(massTimeId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + massTimeId));
		List<MassCoreTeam> massCoreTeamList = request.stream()
				.map(massCoreTeamDTO -> modelMapper.map(massCoreTeamDTO, MassCoreTeam.class))
				.collect(Collectors.toList());
		massCoreTeamList.forEach(massCoreTeam -> massCoreTeam.setMassTime(massTime));
		massCoreTeamList = massCoreTeamRepository.saveAll(massCoreTeamList);
		List<MassCoreTeamDTO> massCoreTeamDTOList = massCoreTeamList.stream()
				.map(massCoreTeam -> modelMapper.map(massCoreTeam, MassCoreTeamDTO.class)).collect(Collectors.toList());
		BaseResponse<MassCoreTeamDTO> response = new BaseResponse<MassCoreTeamDTO>(HttpStatus.CREATED.value(),
				ApplicationConstants.SUCCESS, massCoreTeamDTOList);
		return response;
	}

	@Override
	public BaseResponse<MassCoreTeamDTO> updateMassCoreTeam(Long massTimeId, List<MassCoreTeamDTO> request) {
		LOGGER.debug("Calling MassCoreTeamServiceImpl.updateMassCoreTeam()");
		final MassTime massTime = massTimeRepository.findById(massTimeId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_MASSTIME_NOT_FOUND + massTimeId));
		List<MassCoreTeam> massCoreTeamList = new ArrayList<MassCoreTeam>();
		for (MassCoreTeamDTO massCoreTeamDTO : request) {
			MassCoreTeam massCoreTeam = massCoreTeamRepository.getOne(massCoreTeamDTO.getId());
			if (massCoreTeam == null) {
				massCoreTeam = new MassCoreTeam();
				massCoreTeam.setMassTime(massTime);
			} else {
				if (massCoreTeamDTO.isDelete()) {
					massCoreTeam.setDeleted(true);
				}
			}
			modelMapper.map(massCoreTeamDTO, massCoreTeam);
			massCoreTeamList.add(massCoreTeam);
		}
		massCoreTeamList = massCoreTeamRepository.saveAll(massCoreTeamList);
		List<MassCoreTeamDTO> massCoreTeamDTOList = massCoreTeamList.stream()
				.map(massCoreTeam -> modelMapper.map(massCoreTeam, MassCoreTeamDTO.class)).collect(Collectors.toList());
		BaseResponse<MassCoreTeamDTO> response = new BaseResponse<MassCoreTeamDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, massCoreTeamDTOList);
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
