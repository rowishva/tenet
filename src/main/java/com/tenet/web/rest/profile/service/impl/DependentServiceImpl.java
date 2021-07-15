package com.tenet.web.rest.profile.service.impl;

import java.util.ArrayList;
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
	public BaseResponse<DependentDTO> createDependents(Long profileId, List<DependentDTO> request) {
		LOGGER.debug("Calling DependentServiceImpl.createDependents()");
		final Profile profile = profileRepository.getOne(profileId);
		List<Dependent> dependentList = request.stream()
				.map(dependentDTO -> (Dependent) modelMapper.map(dependentDTO, Dependent.class))
				.collect(Collectors.toList());
		dependentList.forEach(dependent -> dependent.setProfile(profile));
		dependentList = dependentRepository.saveAll(dependentList);
		List<DependentDTO> dependentDTOList = dependentList.stream()
				.map(dependent -> (DependentDTO) modelMapper.map(dependent, DependentDTO.class))
				.collect(Collectors.toList());
		BaseResponse<DependentDTO> response = new BaseResponse<DependentDTO>(HttpStatus.CREATED.value(),
				ApplicationConstants.SUCCESS, dependentDTOList);
		return response;

	}

	@Override
	public BaseResponse<DependentDTO> updateDependents(Long profileId, List<DependentDTO> request) {
		LOGGER.debug("Calling DependentServiceImpl.updateUser()");
		final Profile profile = profileRepository.getOne(profileId);
		List<Dependent> dependentList = new ArrayList<Dependent>();
		for (DependentDTO dependentDTO : request) {
			Dependent dependent = dependentRepository.getOne(dependentDTO.getId());
			if (dependentDTO.isDelete()) {
				dependent.setDeleted(true);
			} else {
				if (dependent == null) {
					dependent = new Dependent();
					dependent.setProfile(profile);
				}
				modelMapper.map(dependentDTO, dependent);
			}
			dependentList.add(dependent);
		}
		dependentList = dependentRepository.saveAll(dependentList);
		List<DependentDTO> dependentDTOList = dependentList.stream()
				.map(dependent -> (DependentDTO) modelMapper.map(dependent, DependentDTO.class))
				.collect(Collectors.toList());
		BaseResponse<DependentDTO> response = new BaseResponse<DependentDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, dependentDTOList);
		return response;
	}

	@Override
	public BaseResponse<DependentDTO> getDependents(Long profileId) {
		LOGGER.debug("Calling DependentServiceImpl.getDependents()");
		List<Dependent> dependentList = dependentRepository.findByProfileId(profileId);
		List<DependentDTO> dependentDTOList = dependentList.stream()
				.map(dependent -> (DependentDTO) modelMapper.map(dependent, DependentDTO.class))
				.collect(Collectors.toList());
		BaseResponse<DependentDTO> response = new BaseResponse<DependentDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, dependentDTOList);
		return response;
	}

}
