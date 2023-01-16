package com.tenet.web.rest.admin.service.impl;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.service.AdminProfileService;
import com.tenet.web.rest.auth.service.AuthUserDetails;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.dto.response.BaseResponsePage;
import com.tenet.web.rest.common.entity.Profile;
import com.tenet.web.rest.common.entity.Role;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.exception.UnauthorizedException;
import com.tenet.web.rest.common.repository.ProfileRepository;
import com.tenet.web.rest.common.repository.RoleRepository;
import com.tenet.web.rest.common.specification.ProfileSerachSpec;
import com.tenet.web.rest.profile.dto.ProfileDTO;

@Service
public class AdminProfileServiceImpl implements AdminProfileService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private RoleRepository roleRepository;

	private static Map<String, List<String>> ROLE_ASSIGN_ELIGIBILITY_MAP = Map.ofEntries(
			new AbstractMap.SimpleEntry<String, List<String>>("SUADMN",
					Arrays.asList(new String[] { "ADMN", "RTADMN", "RTUSR", "USER" })),
			new AbstractMap.SimpleEntry<String, List<String>>("ADMN",
					Arrays.asList(new String[] { "RTADMN", "RTUSR", "USER" })),
			new AbstractMap.SimpleEntry<String, List<String>>("RTADMN",
					Arrays.asList(new String[] { "RTUSR", "USER" })),
			new AbstractMap.SimpleEntry<String, List<String>>("RTUSR", Arrays.asList(new String[] { "USER" })));

	@Override
	public BaseResponse<ProfileDTO> changeRole(Long id, String toRoleCode) {
		LOGGER.debug("Calling AdminProfileServiceImpl.changeRole()");
		if (!roleAssignEligibilityCheck(toRoleCode)) {
			throw new UnauthorizedException(ApplicationConstants.ERROR_MSG_NOT_ELIGIBIL_ROLE_ASSIGNMENT);
		}
		Profile profile = profileRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_PROFILE_NOT_FOUND + id));

		Role role = roleRepository.findByRoleCode(toRoleCode);
		if (role == null) {
			throw new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_ROLE_NOT_FOUND + toRoleCode);
		}
		profile.setRole(role);
		profile = profileRepository.save(profile);
		ProfileDTO profileDTO = (ProfileDTO) modelMapper.map(profile, ProfileDTO.class);
		BaseResponse<ProfileDTO> response = new BaseResponse<ProfileDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS, profileDTO);
		return response;
	}

	@Override
	public BaseResponsePage<ProfileDTO> searchProfile(ProfileSerachSpec spec, Pageable pageable) {
		Page<Profile> profilePage = profileRepository.findAll(spec, pageable);
		BaseResponsePage<ProfileDTO> response = new BaseResponsePage<ProfileDTO>(HttpStatus.OK.value(),
				ApplicationConstants.SUCCESS);
		if (profilePage != null) {
			List<ProfileDTO> profileDTOList = profilePage.stream()
					.map(profile -> modelMapper.map(profile, ProfileDTO.class)).collect(Collectors.toList());
			response.setResponseList(profileDTOList);
			response.setTotalElements(profilePage.getTotalElements());
			response.setTotalPages(profilePage.getTotalPages());
		}
		return response;
	}

	private boolean roleAssignEligibilityCheck(String toRoleCode) {
		AuthUserDetails userDetails = (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String logInUserRoleCode = userDetails.getRoleCode();
		if (logInUserRoleCode.equals("USER")) {
			throw new UnauthorizedException(ApplicationConstants.ERROR_MSG_NOT_ELIGIBIL_ROLE_ASSIGNMENT);
		}
		if (ROLE_ASSIGN_ELIGIBILITY_MAP.get(logInUserRoleCode).contains(toRoleCode)) {
			return true;
		}
		return false;

	}
}
