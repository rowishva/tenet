package com.tenet.web.rest.common.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.PropertyMap;

import com.tenet.web.rest.common.entity.Role;
import com.tenet.web.rest.profile.dto.RoleDTO;

public final class ModelMapPropertyMap {

	private static Logger LOGGER = LogManager.getLogger(ModelMapPropertyMap.class);

	public static PropertyMap<RoleDTO, Role> adminUserRoleDtoToAdminUserRole() {
		return new PropertyMap<RoleDTO, Role>() {
			protected void configure() {
				map().setRoleCode(source.getRoleCode());
				map().setDescription(source.getDescription());
			}
		};
	}
}
