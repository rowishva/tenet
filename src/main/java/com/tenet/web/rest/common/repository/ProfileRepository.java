package com.tenet.web.rest.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenet.web.rest.common.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Profile findByUsername(String username);
}
