package com.tenet.web.rest.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tenet.web.rest.common.entity.Profile;
import com.tenet.web.rest.common.entity.Role;
import com.tenet.web.rest.common.enums.ProfileStatus;
import com.tenet.web.rest.common.exception.UnauthorizedException;
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
		Role role = profile.getRole();
		boolean isEnabled = false;
		if(profile.getStatus().getCode() == ProfileStatus.ACTIVE.getCode()) {
			isEnabled = true;
		}
		else {
			LOGGER.error("Profile is not Active, Status: " + profile.getStatus().getName());
			throw new UnauthorizedException("Profile is not Active, Status: " + profile.getStatus().getName());
		}
		return new AuthUserDetails(profile.getUsername(), profile.getPassword(), getAuthorities(role.getPrivilege()),
				role.getRoleCode(), isEnabled);
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	private Collection<GrantedAuthority> getAuthorities(List<String> privilegeList) {
		Collection<GrantedAuthority> authorities = new ArrayList<>(privilegeList.size());
		for (String privilege : privilegeList) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

}
