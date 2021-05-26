package com.tenet.web.rest.profile.dto;

import com.tenet.web.rest.common.entity.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProfileRequestMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProfileRequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.createTypeMap(ProfileRequestResponse.class, Profile.class)
                .addMappings(mapper -> {
                    mapper.skip(Profile::setId);
                });
    }

    public Profile convertToEntity(ProfileRequestResponse dto) {
        return modelMapper.map(dto, Profile.class);
    }

    public ProfileRequestResponse convertToDto(Profile profile) {
        return modelMapper.map(profile, ProfileRequestResponse.class);
    }

    public Profile merge(ProfileRequestResponse dto, Profile entity) {

        modelMapper.map(dto, entity);
        return entity;
    }
}
