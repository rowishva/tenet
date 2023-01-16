package com.tenet.web.rest.common.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.Role;
import com.tenet.web.rest.common.exception.ResourceNotFoundException;
import com.tenet.web.rest.common.repository.RoleRepository;
import com.tenet.web.rest.common.service.RoleService;
import com.tenet.web.rest.profile.dto.RoleDTO;

@Service
public class RoleServiceImpl implements RoleService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BaseResponse<RoleDTO> createRole(RoleDTO request) {
		LOGGER.debug("Calling RoleServiceImpl.createRole()");
		Role adminRole = modelMapper.map(request, Role.class);
		BaseResponse<RoleDTO> response = null;
		adminRole = roleRepository.save(adminRole);
		RoleDTO adminRoleDTO = modelMapper.map(adminRole, RoleDTO.class);
		response = new BaseResponse<RoleDTO>(HttpStatus.CREATED.value(), ApplicationConstants.SUCCESS, adminRoleDTO);
		return response;

	}

	@Override
	public BaseResponse<RoleDTO> updateRole(long id, RoleDTO request) {
		LOGGER.debug("Calling RoleServiceImpl.updateRole()");
		Role adminRole = roleRepository.getOne(id);
		if (adminRole == null) {
			throw new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_ROLE_NOT_FOUND + id);
		}
		modelMapper.map(request, adminRole);
		adminRole = roleRepository.save(adminRole);
		RoleDTO adminRoleDTO = modelMapper.map(adminRole, RoleDTO.class);
		BaseResponse<RoleDTO> response = new BaseResponse<RoleDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS,
				adminRoleDTO);
		return response;
	}

	@Override
	public BaseResponse<RoleDTO> deleteRole(long id) {
		LOGGER.debug("Calling RoleServiceImpl.deleteRole()");
		Role adminRole = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_ROLE_NOT_FOUND + id));
		adminRole.setDeleted(true);
		adminRole = roleRepository.save(adminRole);
		BaseResponse<RoleDTO> response = new BaseResponse<RoleDTO>(HttpStatus.NO_CONTENT.value(),
				ApplicationConstants.SUCCESS);
		return response;
	}

	@Override
	public BaseResponse<RoleDTO> getRole(long id) {
		LOGGER.debug("Calling RoleServiceImpl.getRole()");
		Role adminRole = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ApplicationConstants.ERROR_MSG_ROLE_NOT_FOUND + id));
		RoleDTO adminRoleDTO = modelMapper.map(adminRole, RoleDTO.class);
		BaseResponse<RoleDTO> response = new BaseResponse<RoleDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS,
				adminRoleDTO);
		return response;
	}

	@Override
	public BaseResponse<RoleDTO> getAllRole() {
		LOGGER.debug("Calling RoleServiceImpl.getAllRole()");
		List<Role> adminRoleList = roleRepository.findAll();
		BaseResponse<RoleDTO> response = new BaseResponse<RoleDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
		if (adminRoleList != null && adminRoleList.size() > 0) {
			List<RoleDTO> adminDTOList = adminRoleList.stream().map(admin -> modelMapper.map(admin, RoleDTO.class))
					.collect(Collectors.toList());
			response.setResponseList(adminDTOList);
		}
		return response;
	}

}
