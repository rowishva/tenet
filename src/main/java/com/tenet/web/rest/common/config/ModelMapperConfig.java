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
		configEnums(modelMapper);
		return modelMapper;
	}

	public void configProperty(ModelMapper modelMapper) {
		/// modelMapper.addMappings(ModelMapPropertyMap.adminUserDtoToAdminUser());
		// modelMapper.addMappings(ModelMapPropertyMap.adminUserRoleDtoToAdminUserRole());
	}

	public void configEnums(ModelMapper modelMapper) {
		modelMapper.addConverter(ModelMapEnumConverter.convertIntToSpecialNeeds());
		modelMapper.addConverter(ModelMapEnumConverter.convertSpecialNeedsToInt());
		modelMapper.addConverter(ModelMapEnumConverter.convertIntToProfileStatus());
		modelMapper.addConverter(ModelMapEnumConverter.convertProfileStatusToInt());
		modelMapper.addConverter(ModelMapEnumConverter.convertIntToMassStatus());
		modelMapper.addConverter(ModelMapEnumConverter.convertMassStatusToInt());

	}
}
