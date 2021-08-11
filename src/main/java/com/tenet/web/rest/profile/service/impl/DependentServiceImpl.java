package com.tenet.web.rest.profile.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.Dependent;
import com.tenet.web.rest.common.entity.Profile;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.DependentRepository;
import com.tenet.web.rest.common.repository.ProfileRepository;
import com.tenet.web.rest.profile.dto.DependentDTO;
import com.tenet.web.rest.profile.service.DependentService;

@Service
public class DependentServiceImpl implements DependentService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private DependentRepository dependentRepository;

	@Override
	@Transactional
	public BaseResponse<DependentDTO> createDependent(Long profileId, DependentDTO request) {
		LOGGER.debug("Calling DependentServiceImpl.createDependent()");
		final Profile profile = profileRepository.findById(profileId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_PROFILE_NOT_FOUND + profileId));
		Dependent dependent = modelMapper.map(request, Dependent.class);
		dependent.setProfile(profile);
		dependent = dependentRepository.save(dependent);
		DependentDTO dependentDTO = modelMapper.map(dependent, DependentDTO.class);
		BaseResponse<DependentDTO> response = new BaseResponse<DependentDTO>(HttpStatus.CREATED.value(),
				ApplicationConstants.SUCCESS, dependentDTO);
		return response;

	}

	@Override
	public BaseResponse<DependentDTO> updateDependent(Long profileId, Long dependentId, DependentDTO request) {
		LOGGER.debug("Calling DependentServiceImpl.updateDependent()");
		Dependent dependent = dependentRepository.findById(dependentId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_DEPENDENT_NOT_FOUND + dependentId));
		modelMapper.map(request, dependent);
		// dependent.setProfile(profile);
		dependent = dependentRepository.save(dependent);
		DependentDTO dependentDTO = modelMapper.map(dependent, DependentDTO.class);
		BaseResponse<DependentDTO> response = new BaseResponse<DependentDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, dependentDTO);
		return response;
	}

	@Override
	public BaseResponse<DependentDTO> deleteDependent(Long profileId, Long dependentId) {
		LOGGER.debug("Calling DependentServiceImpl.deleteDependent()");
		Dependent dependent = dependentRepository.findById(dependentId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_DEPENDENT_NOT_FOUND + dependentId));
		dependent.setDeleted(true);
		// dependent.setProfile(profile);
		dependentRepository.save(dependent);
		BaseResponse<DependentDTO> response = new BaseResponse<DependentDTO>(HttpStatus.NO_CONTENT.value(),
				ApplicationConstants.SUCCESS);
		return response;
	}

	@Override
	public BaseResponse<DependentDTO> getDependent(Long profileId, Long dependentId) {
		LOGGER.debug("Calling DependentServiceImpl.getDependents()");
		Dependent dependent = dependentRepository.findById(dependentId).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_DEPENDENT_NOT_FOUND + dependentId));
		DependentDTO dependentDTO = modelMapper.map(dependent, DependentDTO.class);
		BaseResponse<DependentDTO> response = new BaseResponse<DependentDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, dependentDTO);
		return response;
	}

	@Override
	public BaseResponse<DependentDTO> getAllDependents(Long profileId) {
		LOGGER.debug("Calling DependentServiceImpl.getAllDependents()");
		List<Dependent> dependentList = dependentRepository.findByProfileId(profileId);
		BaseResponse<DependentDTO> response = new BaseResponse<DependentDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		if (dependentList != null) {
			List<DependentDTO> dependentDTOList = dependentList.stream()
					.map(dependent -> modelMapper.map(dependent, DependentDTO.class)).collect(Collectors.toList());
			response.setResponseList(dependentDTOList);
		}
		return response;
	}

}
