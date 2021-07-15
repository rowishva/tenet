package com.tenet.web.rest.profile.service;

import org.springframework.data.domain.Pageable;

import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.ProfileDTO;

public interface ProfileService {

	public BaseResponse<ProfileDTO> createProfile(ProfileDTO request);

	public BaseResponse<ProfileDTO> updateProfile(ProfileDTO request);

	public BaseResponse<ProfileDTO> deleteProfile(Long id);

	public BaseResponse<ProfileDTO> getProfile(Long id);

	public BaseResponse<ProfileDTO> getAllProfile(Pageable pageable);

}
