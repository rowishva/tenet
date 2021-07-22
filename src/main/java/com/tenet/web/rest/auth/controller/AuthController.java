package com.tenet.web.rest.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tenet.web.rest.auth.request.AuthRequest;
import com.tenet.web.rest.auth.response.AuthResponse;
import com.tenet.web.rest.auth.service.AuthUserDetailsService;
import com.tenet.web.rest.auth.util.AuthTokenUtil;
import com.tenet.web.rest.common.ApplicationConstants;
import com.tenet.web.rest.common.ServiceEndpoints;
import com.tenet.web.rest.common.dto.response.BaseResponse;
import com.tenet.web.rest.common.entity.Profile;
import com.tenet.web.rest.common.service.ModelMapperService;
import com.tenet.web.rest.profile.dto.RoleDTO;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthTokenUtil authTokenUtil;

	@Autowired
	private AuthUserDetailsService userDetailsService;

	@Autowired
	private ModelMapperService modelMapperService;

	@RequestMapping(value = ServiceEndpoints.PROFILE_LOGIN, method = RequestMethod.POST)
	public BaseResponse<AuthResponse> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest)
			throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = authTokenUtil.generateToken(userDetails);
		Profile profile = userDetailsService.getProfile();
		RoleDTO roleDTO = (RoleDTO) modelMapperService.convert(profile.getRole(), RoleDTO.class);
		return new BaseResponse<AuthResponse>(HttpStatus.OK.value(), ApplicationConstants.SUCCESS,
				new AuthResponse(token, profile.getFullName(), roleDTO, profile.getId().toString()));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
