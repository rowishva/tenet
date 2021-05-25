package com.tenet.web.rest.common.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.PropertyMap;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenet.web.rest.admin.dto.AdminUserDTO;
import com.tenet.web.rest.admin.dto.AdminUserRoleDTO;
import com.tenet.web.rest.common.entity.AdminUser;
import com.tenet.web.rest.common.entity.AdminUserRole;

public final class ModelMapPropertyMap {

	private static Logger LOGGER = LogManager.getLogger(ModelMapPropertyMap.class);	
	
	public static PropertyMap<AdminUserDTO, AdminUser> adminUserDtoToAdminUser() {
		return new PropertyMap<AdminUserDTO, AdminUser>() {
			protected void configure() {
				map().setFirstName(source.getFirstName());
				map().setLastName(source.getLastName());
			}
		};
	}
	
	public static PropertyMap<AdminUserRoleDTO, AdminUserRole> adminUserRoleDtoToAdminUserRole() {
		return new PropertyMap<AdminUserRoleDTO, AdminUserRole>() {
			protected void configure() {
				map().setRoleCode(source.getRoleCode());
				map().setDescription(source.getDescription());
				/*ObjectMapper mapper = new ObjectMapper();
				mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
				try {					
					map().setPrivilege(mapper.readTree(source.getPrivilege()));
				} catch (JsonProcessingException e) {
					LOGGER.error("Error parsing jsonNodeString", e);
				}*/				
				
			}
		};
	}	
}
