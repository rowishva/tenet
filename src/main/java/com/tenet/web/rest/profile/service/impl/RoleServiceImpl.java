package com.tenet.web.rest.profile.service.impl;

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
import com.tenet.web.rest.common.repository.RoleRepository;
import com.tenet.web.rest.common.service.ModelMapperService;
import com.tenet.web.rest.profile.dto.RoleDTO;
import com.tenet.web.rest.profile.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapperService modelMapperService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BaseResponse<RoleDTO> createRole(RoleDTO request) {
		LOGGER.debug("Calling AdminRoleServiceImpl.createAdminRole()");
		Role adminRole = (Role) modelMapper.map(request, Role.class);
		BaseResponse<RoleDTO> response = null;
		if (adminRole != null) {
			adminRole = roleRepository.save(adminRole);
			RoleDTO adminRoleDTO = (RoleDTO) modelMapperService.convert(adminRole, RoleDTO.class);
			response = new BaseResponse<RoleDTO>(HttpStatus.CREATED.value(), ApplicationConstants.SUCCESS,
					adminRoleDTO);
		} else {
			response = new BaseResponse<RoleDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;

	}

	@Override
	public BaseResponse<RoleDTO> updateRole(RoleDTO request) {
		LOGGER.debug("Calling AdminRoleServiceImpl.updateAdminRole()");
		Role adminRole = roleRepository.getOne(request.getId());
		modelMapper.map(request, adminRole);
		BaseResponse<RoleDTO> response = null;
		if (adminRole != null) {
			adminRole = roleRepository.save(adminRole);
			RoleDTO adminRoleDTO = (RoleDTO) modelMapperService.convert(adminRole, RoleDTO.class);
			response = new BaseResponse<RoleDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS, adminRoleDTO);
		} else {
			response = new BaseResponse<RoleDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<RoleDTO> deleteRole(long id) {
		LOGGER.debug("Calling AdminRoleServiceImpl.deleteAdminRole()");
		Role adminRole = roleRepository.getOne(id);
		BaseResponse<RoleDTO> response = null;
		if (adminRole != null) {
			adminRole.setDeleted(true);
			adminRole = roleRepository.save(adminRole);
			response = new BaseResponse<RoleDTO>(HttpStatus.NO_CONTENT.value(), ApplicationConstants.SUCCESS);
		} else {
			response = new BaseResponse<RoleDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<RoleDTO> getRole(long id) {
		LOGGER.debug("Calling AdminRoleServiceImpl.getAdminRole()");
		Role adminRole = roleRepository.getOne(id);
		BaseResponse<RoleDTO> response = null;
		if (adminRole != null) {
			RoleDTO adminRoleDTO = (RoleDTO) modelMapper.map(adminRole, RoleDTO.class);
			response = new BaseResponse<RoleDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS, adminRoleDTO);
		} else {
			response = new BaseResponse<RoleDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<RoleDTO> getAllRole() {
		LOGGER.debug("Calling AdminRoleServiceImpl.getAllAdminRole()");
		List<Role> adminRoleList = roleRepository.findAll();
		BaseResponse<RoleDTO> response = null;
		if (adminRoleList != null) {
			List<RoleDTO> adminDTOList = adminRoleList.stream()
					.map(admin -> (RoleDTO) modelMapper.map(admin, RoleDTO.class)).collect(Collectors.toList());
			response = new BaseResponse<RoleDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS, adminDTOList);
		} else {
			response = new BaseResponse<RoleDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

}
