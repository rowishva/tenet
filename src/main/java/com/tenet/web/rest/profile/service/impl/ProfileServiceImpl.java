package com.tenet.web.rest.profile.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.Email;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.Dependent;
import com.tenet.web.rest.common.entity.Profile;
import com.tenet.web.rest.common.entity.Role;
import com.tenet.web.rest.common.otp.OTPUtil;
import com.tenet.web.rest.common.repository.ProfileRepository;
import com.tenet.web.rest.common.repository.RoleRepository;
import com.tenet.web.rest.common.service.EmailService;
import com.tenet.web.rest.profile.dto.DependentDTO;
import com.tenet.web.rest.profile.dto.ProfileDTO;
import com.tenet.web.rest.profile.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private EmailService emailService;

	@Override
	@Transactional
	public BaseResponse<ProfileDTO> createProfile(ProfileDTO request) {
		LOGGER.debug("Calling ProfileServiceImpl.createProfile()");
		final Profile profile = (Profile) modelMapper.map(request, Profile.class);
		profile.setPassword(bcryptEncoder.encode(request.getPassword()));
		Role role = roleRepository.findByRoleCode("USER");
		List<DependentDTO> dependentDTOList = request.getDependents();
		List<Dependent> dependentList = dependentDTOList.stream()
				.map(dependentDTO -> (Dependent) modelMapper.map(dependentDTO, Dependent.class))
				.collect(Collectors.toList());
		dependentList.forEach(dependent -> dependent.setProfile(profile));
		profile.setDependents(dependentList);
		BaseResponse<ProfileDTO> response = null;
		if (role != null) {
			profile.setRole(role);
			Profile profileSaved = profileRepository.save(profile);
			LOGGER.debug("Sending Email with OTP");
			sendEmail(profile.getFullName(), profile.getUsername());
			ProfileDTO profileDTO = (ProfileDTO) modelMapper.map(profileSaved, ProfileDTO.class);
			response = new BaseResponse<ProfileDTO>(HttpStatus.CREATED.value(), ApplicationConstants.SUCCESS,
					profileDTO);
		} else {
			response = new BaseResponse<ProfileDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;

	}

	@Override
	public BaseResponse<ProfileDTO> updateProfile(ProfileDTO request) {
		LOGGER.debug("Calling ProfileServiceImpl.updateUser()");
		Profile profile = (Profile) modelMapper.map(request, Profile.class);
		Role role = roleRepository.findByRoleCode(request.getRoleCode());
		BaseResponse<ProfileDTO> response = null;
		if (profile != null && role != null) {
			profile.setRole(role);
			List<DependentDTO> dependentDTOList = request.getDependents();
			List<Dependent> dependentList = dependentDTOList.stream()
					.map(dependent -> (Dependent) modelMapper.map(dependent, Dependent.class))
					.collect(Collectors.toList());
			profile.setDependents(dependentList);
			profile = profileRepository.save(profile);
			ProfileDTO profileDTO = (ProfileDTO) modelMapper.map(profile, ProfileDTO.class);
			response = new BaseResponse<ProfileDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS, profileDTO);
		} else {
			response = new BaseResponse<ProfileDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<ProfileDTO> deleteProfile(Long id) {
		LOGGER.debug("Calling ProfileServiceImpl.deleteProfile()");
		Profile profile = profileRepository.getOne(id);
		BaseResponse<ProfileDTO> response = null;
		if (profile != null) {
			profile.setDeleted(true);
			profile = profileRepository.save(profile);
			response = new BaseResponse<ProfileDTO>(HttpStatus.NO_CONTENT.value(), ApplicationConstants.SUCCESS);
		} else {
			response = new BaseResponse<ProfileDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<ProfileDTO> getProfile(Long id) {
		LOGGER.debug("Calling ProfileServiceImpl.getProfile()");
		Profile profile = profileRepository.getOne(id);
		BaseResponse<ProfileDTO> response = null;
		if (profile != null) {
			ProfileDTO profileDTO = (ProfileDTO) modelMapper.map(profile, ProfileDTO.class);
			response = new BaseResponse<ProfileDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS, profileDTO);
		} else {
			response = new BaseResponse<ProfileDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<ProfileDTO> getAllProfile(Pageable pageable) {
		LOGGER.debug("Calling ProfileServiceImpl.getAllProfile()");
		Page<Profile> profileList = profileRepository.findAll(pageable);
		BaseResponse<ProfileDTO> response = null;
		if (profileList != null) {
			List<ProfileDTO> adminUserDTOList = profileList.stream()
					.map(adminUser -> (ProfileDTO) modelMapper.map(adminUser, ProfileDTO.class))
					.collect(Collectors.toList());
			response = new BaseResponse<ProfileDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS,
					adminUserDTOList);
		} else {
			response = new BaseResponse<ProfileDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	private void sendEmail(String name, String email) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", name);
		model.put("otp", OTPUtil.generateOTP(6));
		Email mail = new Email(email, "no-reply@tenet.com", "Welcome to Tenet", null, model);
		emailService.sendEmailWithTemplate(mail);
	}

}
