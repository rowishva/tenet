package com.tenet.web.rest.auth.service;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.common.entity.Profile;
import com.tenet.web.rest.common.repository.ProfileRepository;

@Service
public class AuthUserDetailsService implements UserDetailsService {

	private Logger LOGGER = LogManager.getLogger(this.getClass());

	@Autowired
	private ProfileRepository profileRepository;

	private Profile profile;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		profile = profileRepository.findByUsername(username);
		if (profile == null) {
			LOGGER.error("Profile not found with username: " + username);
			throw new UsernameNotFoundException("Profile not found with username: " + username);
		}
		return new User(profile.getUsername(), profile.getPassword(), new ArrayList<>());
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
