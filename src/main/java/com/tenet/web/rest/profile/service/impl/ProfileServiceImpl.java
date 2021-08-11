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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.Email;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.Profile;
import com.tenet.web.rest.common.entity.Role;
import com.tenet.web.rest.common.enums.ProfileStatus;
import com.tenet.web.rest.common.exception.BadRequestException;
import com.tenet.web.rest.common.exception.ResourceAlreadyExistsException;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.otp.OTPUtil;
import com.tenet.web.rest.common.repository.ProfileRepository;
import com.tenet.web.rest.common.repository.RoleRepository;
import com.tenet.web.rest.common.service.EmailService;
import com.tenet.web.rest.profile.dto.ProfileDTO;
import com.tenet.web.rest.profile.dto.ProfileUpdateDTO;
import com.tenet.web.rest.profile.dto.SetNewPasswordDTO;
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

	@Value("${profile.create.send.email.from}")
	private String emailFrom;

	@Value("${profile.create.send.email.title}")
	private String emailTitle;

	@Override
	@Transactional
	public BaseResponse<ProfileDTO> createProfile(ProfileDTO request) {
		LOGGER.debug("Calling ProfileServiceImpl.createProfile()");
		Profile usernameCheck = profileRepository.findByUsername(request.getUsername());
		if (usernameCheck != null) {
			throw new ResourceAlreadyExistsException(
					ApplicationConstants.ERROR_MSG_USERNAME_ALREADY_EXISTS + request.getUsername());
		}
		final Profile profile = (Profile) modelMapper.map(request, Profile.class);
		profile.setPassword(bcryptEncoder.encode(request.getPassword()));
		Role role = roleRepository.findByRoleCode("USER");
		profile.setRole(role);
		profile.setStatus(ProfileStatus.OTPVERIFICATION);

		String otp = OTPUtil.generateOTP(6);
		LOGGER.debug("Generate OTP" + otp);
		profile.setOtp(otp);
		Profile profileSaved = profileRepository.save(profile);
		LOGGER.debug("Sending Email with OTP");
		sendEmail(profile.getFullName(), profile.getUsername(), otp, 1);
		ProfileDTO profileDTO = modelMapper.map(profileSaved, ProfileDTO.class);
		BaseResponse<ProfileDTO> response = new BaseResponse<ProfileDTO>(HttpStatus.CREATED.value(),
				ApplicationConstants.SUCCESS, profileDTO);
		return response;

	}

	@Override
	public BaseResponse<ProfileDTO> updateProfile(Long id, ProfileUpdateDTO request) {
		LOGGER.debug("Calling ProfileServiceImpl.updateUser()");
		Profile profile = profileRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_PROFILE_NOT_FOUND + id));
		modelMapper.map(request, profile);
		profile = profileRepository.save(profile);
		ProfileDTO profileDTO = (ProfileDTO) modelMapper.map(profile, ProfileDTO.class);
		BaseResponse<ProfileDTO> response = new BaseResponse<ProfileDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, profileDTO);
		return response;
	}

	@Override
	public BaseResponse<ProfileDTO> deleteProfile(Long id) {
		LOGGER.debug("Calling ProfileServiceImpl.deleteProfile()");
		Profile profile = profileRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_PROFILE_NOT_FOUND + id));
		BaseResponse<ProfileDTO> response = null;
		profile.setDeleted(true);
		profile = profileRepository.save(profile);
		response = new BaseResponse<ProfileDTO>(HttpStatus.NO_CONTENT.value(), ApplicationConstants.SUCCESS);
		return response;
	}

	@Override
	public BaseResponse<ProfileDTO> getProfile(Long id) {
		LOGGER.debug("Calling ProfileServiceImpl.getProfile()");
		Profile profile = profileRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_PROFILE_NOT_FOUND + id));
		BaseResponse<ProfileDTO> response = null;
		ProfileDTO profileDTO = modelMapper.map(profile, ProfileDTO.class);
		response = new BaseResponse<ProfileDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS, profileDTO);
		return response;
	}

	@Override
	public BaseResponse<ProfileDTO> getAllProfile(Pageable pageable) {
		LOGGER.debug("Calling ProfileServiceImpl.getAllProfile()");
		Page<Profile> profileList = profileRepository.findAll(pageable);
		BaseResponse<ProfileDTO> response = new BaseResponse<ProfileDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		if (profileList != null) {
			List<ProfileDTO> profileDTOList = profileList.stream()
					.map(profileDTO -> modelMapper.map(profileDTO, ProfileDTO.class)).collect(Collectors.toList());
			response.setResponseList(profileDTOList);
		}
		return response;
	}

	@Override
	public BaseResponse<ProfileDTO> sendNewOtp(String username) {
		LOGGER.debug("Calling ProfileServiceImpl.sendNewOtp()");
		Profile profile = profileRepository.findByUsername(username);
		if (profile == null) {
			throw new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_USERNAME_NOT_FOUND + username);
		}
		String otp = OTPUtil.generateOTP(6);
		LOGGER.debug("Generate OTP" + otp);
		profile.setOtp(otp);
		profileRepository.save(profile);
		LOGGER.debug("Sending Email with OTP");
		sendEmail(profile.getFullName(), profile.getUsername(), otp, 2);
		BaseResponse<ProfileDTO> response = new BaseResponse<ProfileDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		return response;
	}

	@Override
	public BaseResponse<ProfileDTO> forgotPassword(String username) {
		LOGGER.debug("Calling ProfileServiceImpl.forgotPassword()");
		Profile profile = profileRepository.findByUsername(username);
		if (profile == null) {
			throw new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_USERNAME_NOT_FOUND + username);
		}
		String otp = OTPUtil.generateOTP(6);
		LOGGER.debug("Generate OTP" + otp);
		profile.setOtp(otp);
		profileRepository.save(profile);
		LOGGER.debug("Sending Email with OTP");
		sendEmail(profile.getFullName(), profile.getUsername(), otp, 3);
		BaseResponse<ProfileDTO> response = new BaseResponse<ProfileDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		return response;
	}

	@Override
	public BaseResponse<ProfileDTO> setNewPassword(String username, SetNewPasswordDTO request) {
		LOGGER.debug("Calling ProfileServiceImpl.setNewPassword()");
		Profile profile = profileRepository.findByUsername(username);
		if (profile == null) {
			throw new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_USERNAME_NOT_FOUND + username);
		}
		profile.setPassword(bcryptEncoder.encode(request.getPassword()));
		profileRepository.save(profile);
		BaseResponse<ProfileDTO> response = new BaseResponse<ProfileDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		return response;
	}

	@Override
	public BaseResponse<ProfileDTO> otpVerification(String username, String otp) {
		LOGGER.debug("Calling ProfileServiceImpl.otpVerification()");
		Profile profile = profileRepository.findByUsername(username);
		if (profile == null) {
			throw new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_USERNAME_NOT_FOUND + username);
		}
		BaseResponse<ProfileDTO> response = null;
		if (otp != null && otp.equals(profile.getOtp())) {
			profile.setOtp(null);
			if (ProfileStatus.OTPVERIFICATION.equals(profile.getStatus())) {
				profile.setStatus(ProfileStatus.ACTIVE);
			}
			profileRepository.save(profile);
			response = new BaseResponse<ProfileDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
		} else {
			throw new BadRequestException(ApplicationConstants.ERROR_MSG_INVALID_OTP + otp);
		}
		return response;
	}

	private void sendEmail(String name, String email, String otp, int template) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", name);
		model.put("otp", otp);
		Email mail = new Email(email, emailFrom, emailTitle, null, model);
		emailService.sendEmailWithTemplate(mail, template);
	}

}
