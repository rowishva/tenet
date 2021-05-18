package com.tenet.web.rest.admin.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.admin.dto.request.AdminUserDTO;
import com.tenet.web.rest.admin.service.AdminService;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.AdminUser;
import com.tenet.web.rest.common.repository.AdminUserRepository;
import com.tenet.web.rest.common.service.ModelMapperService;

@Service
public class AdminServiceImpl implements AdminService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ModelMapperService modelMapperService;

	@Autowired
	private AdminUserRepository adminUserRepository;

	@Override
	public BaseResponse createAdminUser(AdminUserDTO request) {
		LOGGER.debug("Calling AdminUserCreateResponse.createAdminUser()");
		AdminUser adminUser = (AdminUser) modelMapperService.convert(request, AdminUser.class);
		adminUser = adminUserRepository.save(adminUser);
		BaseResponse response = null;
		if (adminUser != null) {
			response = new BaseResponse(ApplicationConstants.STATUS_CODE_CREATED, ApplicationConstants.SUCCESS,
					adminUser);
		} else {
			response = new BaseResponse(ApplicationConstants.STATUS_CODE_CONFLICT, ApplicationConstants.ERROR);
		}
		return response;

	}

}
