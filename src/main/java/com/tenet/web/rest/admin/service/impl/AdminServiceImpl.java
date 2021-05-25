package com.tenet.web.rest.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.dto.AdminUserDTO;
import com.tenet.web.rest.admin.service.AdminService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.AdminUser;
import com.tenet.web.rest.common.entity.AdminUserRole;
import com.tenet.web.rest.common.repository.AdminUserRepository;
import com.tenet.web.rest.common.repository.AdminUserRoleRepository;
import com.tenet.web.rest.common.service.ModelMapperService;

@Service
public class AdminServiceImpl implements AdminService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapperService modelMapperService;

	@Autowired
	private AdminUserRepository adminUserRepository;

	@Autowired
	private AdminUserRoleRepository adminUserRoleRepository;

	@Override
	public BaseResponse<AdminUserDTO> createAdminUser(AdminUserDTO request) {
		LOGGER.debug("Calling AdminServiceImpl.createAdminUser()");
		AdminUser adminUser = (AdminUser) modelMapperService.convert(request, AdminUser.class);
		AdminUserRole adminUserRole = adminUserRoleRepository.findByRoleCode(request.getRoleCode());
		BaseResponse<AdminUserDTO> response = null;
		if (adminUser != null && adminUserRole != null) {
			adminUser.setRoleId(adminUserRole);
			adminUser = adminUserRepository.save(adminUser);
			AdminUserDTO adminUserDTO = (AdminUserDTO) modelMapperService.convert(adminUser, AdminUserDTO.class);
			response = new BaseResponse<AdminUserDTO>(HttpStatus.CREATED.value(), ApplicationConstants.SUCCESS,
					adminUserDTO);
		} else {
			response = new BaseResponse<AdminUserDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;

	}

	@Override
	public BaseResponse<AdminUserDTO> updateAdminUser(AdminUserDTO request) {
		LOGGER.debug("Calling AdminServiceImpl.updateAdminUser()");
		AdminUser adminUser = (AdminUser) modelMapperService.convert(request, AdminUser.class);
		AdminUserRole adminUserRole = adminUserRoleRepository.findByRoleCode(request.getRoleCode());
		BaseResponse<AdminUserDTO> response = null;
		if (adminUser != null && adminUserRole != null) {
			adminUser.setRoleId(adminUserRole);
			adminUser = adminUserRepository.save(adminUser);
			AdminUserDTO adminUserDTO = (AdminUserDTO) modelMapperService.convert(adminUser, AdminUserDTO.class);
			response = new BaseResponse<AdminUserDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS,
					adminUserDTO);
		} else {
			response = new BaseResponse<AdminUserDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<AdminUserDTO> deleteAdminUser(long id) {
		LOGGER.debug("Calling AdminServiceImpl.deleteAdminUser()");
		AdminUser adminUser = adminUserRepository.getOne(id);
		BaseResponse<AdminUserDTO> response = null;
		if (adminUser != null) {
			adminUser.setDeleted(true);
			adminUser = adminUserRepository.save(adminUser);
			response = new BaseResponse<AdminUserDTO>(HttpStatus.NO_CONTENT.value(), ApplicationConstants.SUCCESS);
		} else {
			response = new BaseResponse<AdminUserDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<AdminUserDTO> getAdminUser(long id) {
		LOGGER.debug("Calling AdminServiceImpl.getAdminUser()");
		AdminUser adminUser = adminUserRepository.getOne(id);
		BaseResponse<AdminUserDTO> response = null;
		if (adminUser != null) {
			AdminUserDTO adminUserDTO = (AdminUserDTO) modelMapperService.convert(adminUser, AdminUserDTO.class);
			response = new BaseResponse<AdminUserDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS,
					adminUserDTO);
		} else {
			response = new BaseResponse<AdminUserDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

	@Override
	public BaseResponse<AdminUserDTO> getAllAdminUser() {
		LOGGER.debug("Calling AdminServiceImpl.getAllAdminUser()");
		List<AdminUser> adminUserList = adminUserRepository.findAll();
		BaseResponse<AdminUserDTO> response = null;
		if (adminUserList != null) {
			List<AdminUserDTO> adminUserDTOList = adminUserList.stream()
					.map(adminUser -> (AdminUserDTO) modelMapperService.convert(adminUser, AdminUserDTO.class))
					.collect(Collectors.toList());
			response = new BaseResponse<AdminUserDTO>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS,
					adminUserDTOList);
		} else {
			response = new BaseResponse<AdminUserDTO>(HttpStatus.CONFLICT.value(), ApplicationConstants.ERROR);
		}
		return response;
	}

}
