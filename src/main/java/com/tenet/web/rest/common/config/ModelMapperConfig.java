package com.tenet.web.rest.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		configProperty(modelMapper);
		return modelMapper;
	}

	public void configProperty(ModelMapper modelMapper) {
		modelMapper.addMappings(ModelMapPropertyMap.adminUserDtoToAdminUser());
		modelMapper.addMappings(ModelMapPropertyMap.adminUserRoleDtoToAdminUserRole());
	}
}
