package com.tenet.web.rest.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenet.web.rest.admin.dto.AdminUserRoleDTO;
import com.tenet.web.rest.admin.service.AdminRoleService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.AdminUserRole;
import com.tenet.web.rest.common.repository.AdminUserRoleRepository;
import com.tenet.web.rest.common.service.ModelMapperService;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapperService modelMapperService;

	@Autowired
	private AdminUserRoleRepository adminUserRoleRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BaseResponse<AdminUserRoleDTO> createAdminUserRole(AdminUserRoleDTO request) {
		LOGGER.debug("Calling AdminRoleServiceImpl.createAdminUserRole()");
		AdminUserRole adminUserRole = (AdminUserRole) modelMapper.map(request, AdminUserRole.class);
		BaseResponse<AdminUserRoleDTO> response = null;
		if (adminUserRole != null) {
			adminUserRole = adminUserRoleRepository.save(adminUserRole);
			AdminUserRoleDTO adminUserRoleDTO = (AdminUserRoleDTO) modelMapperService.convert(adminUserRole,
					AdminUserRoleDTO.class);
			response = new BaseResponse<AdminUserRoleDTO>(HttpStatus.CREATED.value(), ApplicationConstants.SUCCESS,
					adminUserRoleDTO);
		} else {
			response = new BaseResponse<AdminUserRoleDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;

	}

	@Override
	public BaseResponse<AdminUserRoleDTO> updateAdminUserRole(AdminUserRoleDTO request) {
		LOGGER.debug("Calling AdminRoleServiceImpl.updateAdminUserRole()");
		AdminUserRole adminUserRole = adminUserRoleRepository.getOne(request.getId());
		modelMapper.map(request, adminUserRole);
		BaseResponse<AdminUserRoleDTO> response = null;
		if (adminUserRole != null) {
			adminUserRole = adminUserRoleRepository.save(adminUserRole);
			AdminUserRoleDTO adminUserRoleDTO = (AdminUserRoleDTO) modelMapperService.convert(adminUserRole,
					AdminUserRoleDTO.class);
			response = new BaseResponse<AdminUserRoleDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS,
					adminUserRoleDTO);
		} else {
			response = new BaseResponse<AdminUserRoleDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<AdminUserRoleDTO> deleteAdminUserRole(long id) {
		LOGGER.debug("Calling AdminRoleServiceImpl.deleteAdminUserRole()");
		AdminUserRole adminUserRole = adminUserRoleRepository.getOne(id);
		BaseResponse<AdminUserRoleDTO> response = null;
		if (adminUserRole != null) {
			adminUserRole.setDeleted(true);
			adminUserRole = adminUserRoleRepository.save(adminUserRole);
			response = new BaseResponse<AdminUserRoleDTO>(HttpStatus.NO_CONTENT.value(), ApplicationConstants.SUCCESS);
		} else {
			response = new BaseResponse<AdminUserRoleDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<AdminUserRoleDTO> getAdminUserRole(long id) {
		LOGGER.debug("Calling AdminRoleServiceImpl.getAdminUserRole()");
		AdminUserRole adminUserRole = adminUserRoleRepository.getOne(id);
		BaseResponse<AdminUserRoleDTO> response = null;
		if (adminUserRole != null) {
			AdminUserRoleDTO adminUserRoleDTO = (AdminUserRoleDTO) modelMapper.map(adminUserRole,
					AdminUserRoleDTO.class);
			response = new BaseResponse<AdminUserRoleDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS,
					adminUserRoleDTO);
		} else {
			response = new BaseResponse<AdminUserRoleDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<AdminUserRoleDTO> getAllAdminUserRole() {
		LOGGER.debug("Calling AdminRoleServiceImpl.getAllAdminUserRole()");
		List<AdminUserRole> adminUserRoleList = adminUserRoleRepository.findAll();
		BaseResponse<AdminUserRoleDTO> response = null;
		if (adminUserRoleList != null) {
			List<AdminUserRoleDTO> adminUserDTOList = adminUserRoleList.stream()
					.map(adminUser -> (AdminUserRoleDTO) modelMapper.map(adminUser, AdminUserRoleDTO.class))
					.collect(Collectors.toList());
			response = new BaseResponse<AdminUserRoleDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS,
					adminUserDTOList);
		} else {
			response = new BaseResponse<AdminUserRoleDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

}
