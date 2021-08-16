package com.tenet.web.rest.profile.service;

import org.springframework.data.domain.Pageable;

import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.ForgotPasswordResponse;
import com.tenet.web.rest.profile.dto.ProfileDTO;
import com.tenet.web.rest.profile.dto.ProfileUpdateDTO;
import com.tenet.web.rest.profile.dto.SetNewPasswordDTO;

public interface ProfileService {

	public BaseResponse<ProfileDTO> createProfile(ProfileDTO request);

	public BaseResponse<ProfileDTO> updateProfile(Long id, ProfileUpdateDTO request);

	public BaseResponse<ProfileDTO> deleteProfile(Long id);

	public BaseResponse<ProfileDTO> getProfile(Long id);

	public BaseResponse<ProfileDTO> getAllProfile(Pageable pageable);

	public BaseResponse<ProfileDTO> sendNewOtp(String username);

	public BaseResponse<ProfileDTO> forgotPassword(String username);

	public BaseResponse<ProfileDTO> setNewPassword(String username, SetNewPasswordDTO request, String resetToken);

	public BaseResponse<ProfileDTO> otpVerification(String username, String otp);

	public BaseResponse<ForgotPasswordResponse> otpVerificationForgotPassword(String username, String otp);

}
