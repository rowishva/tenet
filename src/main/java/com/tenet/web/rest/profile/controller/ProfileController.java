package com.tenet.web.rest.profile.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.profile.dto.ProfileDTO;
import com.tenet.web.rest.profile.dto.ProfileUpdateDTO;
import com.tenet.web.rest.profile.dto.SetNewPasswordDTO;
import com.tenet.web.rest.profile.service.ProfileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Profile")
@RestController
@RequestMapping(ServiceEndpoints.PROFILE)
public class ProfileController {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ProfileService service;

	@ApiOperation(value = "Create new Profile", response = BaseResponse.class)
	@PostMapping(value = ServiceEndpoints.REGISTER)
	public BaseResponse<ProfileDTO> createProfile(@Valid @RequestBody ProfileDTO request) {
		LOGGER.debug("Calling ProfileController.createProfile()");
		return service.createProfile(request);
	}

	@ApiOperation(value = "Update Profile", response = BaseResponse.class)
	@PutMapping(value = ServiceEndpoints.ID)
	public BaseResponse<ProfileDTO> updateProfile(@PathVariable("id") long id, @RequestBody ProfileUpdateDTO request) {
		LOGGER.debug("Calling ProfileController.updateProfile()");
		return service.updateProfile(id, request);
	}

	@ApiOperation(value = "Delete Profile", response = BaseResponse.class)
	@DeleteMapping(value = ServiceEndpoints.ID)
	public BaseResponse<ProfileDTO> deleteProfile(@PathVariable("id") long id) {
		LOGGER.debug("Calling ProfileController.deleteProfile()");
		return service.deleteProfile(id);
	}

	@ApiOperation(value = "Get Profile", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.ID)
	public BaseResponse<ProfileDTO> getProfile(@PathVariable("id") long id) {
		LOGGER.debug("Calling ProfileController.getProfile()");
		return service.getProfile(id);
	}

	@ApiOperation(value = "Retrieve List of Profile", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.PAGE)
	public BaseResponse<ProfileDTO> getAllProfile(@PathVariable("pageNo") Integer pageNo,
			@PathVariable("pageSize") Integer pageSize) {
		LOGGER.debug("Calling ProfileController.getAllProfile()");
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return service.getAllProfile(pageable);
	}

	@ApiOperation(value = "Send new Otp", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.PROFILE_SEND_NEW_OTP)
	public BaseResponse<ProfileDTO> sendNewOtp(@PathVariable("username") String username) {
		LOGGER.debug("Calling ProfileController.sendNewOtp()");
		return service.sendNewOtp(username);
	}

	@ApiOperation(value = "Forgot Password", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.PROFILE_FORGOT_PASSWORD)
	public BaseResponse<ProfileDTO> forgotPassword(@PathVariable("username") String username) {
		LOGGER.debug("Calling ProfileController.forgotPassword()");
		return service.forgotPassword(username);
	}

	@ApiOperation(value = "Set New Password", response = BaseResponse.class)
	@PutMapping(value = ServiceEndpoints.PROFILE_SET_NEW_PASSWORD)
	public BaseResponse<ProfileDTO> setNewPassword(@PathVariable("username") String username,
			@RequestBody SetNewPasswordDTO request) {
		LOGGER.debug("Calling ProfileController.setNewPassword()");
		return service.setNewPassword(username, request);
	}

	@ApiOperation(value = "OTP Verification", response = BaseResponse.class)
	@GetMapping(value = ServiceEndpoints.PROFILE_OTP_VERIFICATION)
	public BaseResponse<ProfileDTO> otpVerification(@PathVariable("username") String username,
			@PathVariable("otp") String otp) {
		LOGGER.debug("Calling ProfileController.otpVerification()");
		return service.otpVerification(username, otp);
	}

}
